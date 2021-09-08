import { Component, OnInit } from '@angular/core';
import {Sample} from "../../../assets/model/Sample.model";
import {SampleService} from "../../services/sample.service";
import { NzMessageService } from 'ng-zorro-antd/message';


@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  id: string;
  name: string;
  code: number;

  constructor(
    private sampleService: SampleService,
    private message: NzMessageService
  ) { }

  ngOnInit() {
  }

  insert() {
    let newSample = new Sample();
    newSample.setAll(this.id, this.name, this.code);
    let loading = this.message.loading('Action in progress..', { nzDuration: 0 }).messageId;

    this.sampleService.insertSample(newSample)
      .subscribe(
        () => {
          this.message.remove(loading);
          this.message.success(`插入成功`);
        },
        error => {
          console.log(error);
          this.message.remove(loading);
          this.message.error('插入失败');
        }
      )
  }

  delete() {
    let loading = this.message.loading('Action in progress..', { nzDuration: 0 }).messageId;
    this.sampleService.deleteSampleById(this.id)
      .subscribe(
        () => {
          this.message.remove(loading);
          this.message.success('删除成功');
        },
        error => {
          this.message.remove(loading);
          this.message.error('删除失败');
        }
      )
  }

  update() {
    let newSample = new Sample();
    newSample.setAll(this.id, this.name, this.code);
    let loading = this.message.loading('Action in progress..', { nzDuration: 0 }).messageId;

    this.sampleService.updateSample(newSample)
      .subscribe(
        () => {
          this.message.remove(loading);
          this.message.success('更新成功');
        },
        error => {
          this.message.remove(loading);
          this.message.error('更新失败');
        }
      )
  }

  getById() {
    let loading = this.message.loading('Action in progress..', { nzDuration: 0 }).messageId;
    this.sampleService.getSampleById(this.id)
      .subscribe(
        (sample: Sample) => {
          this.message.remove(loading);
          this.id = sample.id;
          this.name = sample.name;
          this.code = sample.code;
          this.message.success('获取成功');
        },
        error => {
          this.message.remove(loading);
          this.message.error('获取失败');
        }
      )
  }
}
