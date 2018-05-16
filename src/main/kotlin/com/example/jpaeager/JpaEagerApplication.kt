package com.example.jpaeager

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class JpaEagerApplication

fun main(args: Array<String>) {
    SpringApplication.run(JpaEagerApplication::class.java, *args)
}
