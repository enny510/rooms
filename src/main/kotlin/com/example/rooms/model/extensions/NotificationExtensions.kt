package com.example.rooms.model.extensions

import com.example.rooms.model.Notification
import javax.websocket.Session

fun Notification.broadcastFor(clients: Set<Session>) {
    clients.forEach {
        this.sendTo(it)
    }
}