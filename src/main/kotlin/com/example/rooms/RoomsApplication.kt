package com.example.rooms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class RoomsApplication

fun main(args: Array<String>) {
	runApplication<RoomsApplication>(*args)
}
