package com.msc.mapper;

import com.msc.pojo.SamplePojo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SampleMapper {

    @Select("select * from sample order by code")
    @Results(id = "sampleMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "code", property = "code")
    })
    List<SamplePojo> getAll();

    @Select("select * from sample where code = #{code}")
    @ResultMap(value = {"sampleMap"})   //使用上面已经创建好的别名映射表
    List<SamplePojo> getByCode(int code);
}
