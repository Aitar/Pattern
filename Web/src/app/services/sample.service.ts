import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Sample} from "../../assets/model/Sample.model";
import {HttpClient} from '@angular/common/http';
import {map, switchMap, take, tap} from 'rxjs/operators';
import {serverUrl} from '../../assets/config';

@Injectable({
  providedIn: 'root'
})
export class SampleService {
  serverUrl: string = serverUrl;
  private _samples = new BehaviorSubject<Sample[]>([]);   //采用rxjs方式进行异步调用

  constructor(private http: HttpClient) {
  }

  get samples() {
    return this._samples.asObservable();    //将_samples作为Observable进行返回
  }

  getAllSamples() {
    return this.http.get(this.serverUrl + '')   //通过url获取对象
      .pipe(
        map(res => {                        //通过map方法对获取的对象处理
          let samples = [];
          for (let key in res) {
            if (res.hasOwnProperty(key)) {
              let sample = new Sample();
              sample.id = res[key].id;
              sample.name = res[key].name;
              sample.code = res[key].code;
              samples.push(sample);
            } //if
          } //for
          return samples;
        }),
        tap(samples => {
          this._samples.next(samples);            //将处理好的samples列表发送到订阅者那里
        })
      );
  }

  getSampleById(id: string) {
    return this.http.get<Sample>(`${this.serverUrl}/sample/${id}`)
      .pipe(
        map(res => {
          let sample = new Sample();
          sample.id = res.id;
          sample.name = res.name;
          sample.code = res.code;
          return sample;
        })
        // map(sample => {
        //   this._samples.next(sample);
        // })
        // 为什么一个对象可以不用tap？
      )
  }

  insertSample(sample: Sample) {
    return this.http.post<{ massage: string }>(`${this.serverUrl}/sample`, sample)  //通过post方法上传sample
      .pipe(
        //在当前页面的内存中也将这个新的sample插入
        switchMap(() => {
          return this.samples;
        }),
        take(1),
        map(samples => {
          this._samples.next(samples.concat(sample));   //加上sample
        })
      )
  }

  deleteSampleById(id: string) {
    return this.http.delete(`${this.serverUrl}/sample/${id}`)
      .pipe(
        switchMap(() => {
          return this.samples;
        }),
        take(1),
        map(samples => {  //这里用map和tap有什么区别？
          for(let i = 0; i < samples.length; i++) {
            if(samples[i].id == id) {
              samples.splice(i, 1);
              this._samples.next(samples);
            }
          }
        })
      )
  }

  updateSample(sample: Sample) {
    return this.http.put(`${this.serverUrl}/sample`, sample)
      .pipe(
        switchMap(() => {
          return this.samples;
        }),
        take(1),
        tap(samples => {
          for(let i = 0; i < samples.length; i++) {
            if(sample.id == samples[i].id) {
              samples[i] = sample;
              this._samples.next(samples);
            }
          }
        })
      )
  }
}
