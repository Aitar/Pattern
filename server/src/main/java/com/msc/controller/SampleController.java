package com.msc.controller;

import com.msc.mapper.SampleMapper;
import com.msc.pojo.SamplePojo;
import com.msc.service.SampleService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin    //允许跨域访问
public class SampleController {

    final private SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/all-sample")
    public List<SamplePojo> getAll() {
        return sampleService.getAll();
    }

    @GetMapping("/sample/{id}")
    public SamplePojo getById(@PathVariable String id) {
        return sampleService.getById(id);
    }

    @PostMapping("/sample")
    public void insert(@RequestBody HashMap<String, String> map){
        sampleService.insertSample(map);
    }

    @DeleteMapping("/sample/{id}")
    public void delete(@PathVariable String id){
        sampleService.deleteSample(id);
    }

    @PutMapping("/sample")
    public void update(@RequestBody HashMap<String, String> map) {
        sampleService.updateSample(map);
    }
}
