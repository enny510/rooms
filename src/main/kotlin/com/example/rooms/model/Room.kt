package com.example.rooms.model

import javax.websocket.Session

data class Room(val name: String, var isLightOn: Boolean = false, var visitors: Set<Session> = emptySet())