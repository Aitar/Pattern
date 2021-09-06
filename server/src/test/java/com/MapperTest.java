package com;

import com.msc.mapper.SampleMapper;
import com.msc.pojo.SamplePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperTest {

    @Autowired
    SampleMapper SampleMapper;

    public void test() {
        List<SamplePojo> all = SampleMapper.getAll();
        System.out.println(all.toString());
    }
}
