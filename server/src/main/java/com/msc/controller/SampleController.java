package com.msc.controller;

import com.msc.mapper.SampleMapper;
import com.msc.pojo.SamplePojo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin    //允许跨域访问
public class SampleController {

    final private SampleMapper sampleMapper;

    public SampleController(SampleMapper sampleMapper) {
        this.sampleMapper = sampleMapper;
    }

    @GetMapping("/all-sample")
    public List<SamplePojo> getAll() {
        return sampleMapper.getAll();
    }

    @PostMapping("/post-sample")
    public String isCollected(@RequestBody HashMap<String, String> map){
        //TODO: 处理POST方法传来的Map，对应的key-value
        return "success";
    }

    @DeleteMapping("/delete-sample/{id}")
    public String delectCollect(@PathVariable String id){
        //TODO: 处理删除的方法
        return "成功删除一条收藏信息";
    }
}
