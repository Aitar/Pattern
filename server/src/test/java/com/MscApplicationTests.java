package com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBootConfiguration
class MscApplicationTests {

    MapperTest mapperTest = new MapperTest();

    @Test
    void contextLoads() {
       mapperTest.test();
    }

}
