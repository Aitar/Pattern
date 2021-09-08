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

    @Select("select * from sample where id = #{id}")
    @ResultMap(value = {"sampleMap"})   //使用上面已经创建好的别名映射表
    SamplePojo getById(String id);

    @Select("select * from sample where code = #{code}")
    @ResultMap(value = {"sampleMap"})   //使用上面已经创建好的别名映射表
    List<SamplePojo> getByCode(int code);

    @Delete("delete from sample where id = #{id}")
    @ResultMap(value = {"sampleMap"})
    void deleteById(String id);

    @Insert("insert into sample values (#{id}, #{name}, #{code})")
    @ResultMap(value = {"sampleMap"})
    void insert(String id, String name, int code);

    @Update("update sample set name = #{name}, code = #{code} where id = #{id}")
    @ResultMap(value = {"sampleMap"})
    void update(String id, String name, int code);
}
