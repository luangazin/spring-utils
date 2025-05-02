package br.com.gazintech.plugin.spring_utils

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@EnableRedisRepositories
@EnableAspectJAutoProxy
@SpringBootApplication
class SpringUtilsApplication

fun main(args: Array<String>) {
	runApplication<SpringUtilsApplication>(*args)
}
