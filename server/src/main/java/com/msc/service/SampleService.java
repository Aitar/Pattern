package com.msc.service;

import com.msc.mapper.SampleMapper;
import com.msc.pojo.SamplePojo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Component
@Transactional
public class SampleService {
    //TODO:在这里写服务
    final private SampleMapper sampleMapper;

    public SampleService(SampleMapper sampleMapper) {
        this.sampleMapper = sampleMapper;
    }

    public List<SamplePojo> getAll() {
        return sampleMapper.getAll();
    }

    public SamplePojo getById(String id) {
        return sampleMapper.getById(id);
    }

    public void insertSample(HashMap<String, String> map) {
        System.out.println();
        sampleMapper.insert(map.get("id"), map.get("name"), Integer.parseInt(map.get("code")));
    }

    public void deleteSample(String id) {
        sampleMapper.deleteById(id);
    }

    public void updateSample(HashMap<String, String> map) {
        sampleMapper.update(map.get("id"), map.get("name"), Integer.parseInt(map.get("code")));
    }
}
