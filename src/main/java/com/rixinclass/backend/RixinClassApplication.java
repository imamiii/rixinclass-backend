package com.rixinclass.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 */
@SpringBootApplication
@MapperScan("com.rixinclass.backend.mapper") // 扫描所有Mapper接口
public class RixinClassApplication {
	public static void main(String[] args) {
		SpringApplication.run(RixinClassApplication.class, args);
	}
}