package com.gao.mall.tiny.malltiny01.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.gao.mall.tiny.malltiny01.mbg.mapper")
public class MyBatisConfig {
}
