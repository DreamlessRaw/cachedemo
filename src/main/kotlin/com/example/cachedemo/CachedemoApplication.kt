package com.example.cachedemo

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
@MapperScan("com.example.cachedemo.mapper")
class CachedemoApplication

fun main(args: Array<String>) {
    runApplication<CachedemoApplication>(*args)
}
