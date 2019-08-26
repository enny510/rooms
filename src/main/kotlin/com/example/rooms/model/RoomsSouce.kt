package com.example.rooms.model

object RoomsSource {

    val rooms: List<Room> by lazy {
        listOf("kitchen", "bathroom").map { Room(it) }
    }
}