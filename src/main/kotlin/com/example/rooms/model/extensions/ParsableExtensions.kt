package com.example.rooms.model.extensions

import com.example.rooms.model.Parsable
import com.example.rooms.saveInvoke
import javax.websocket.Session

fun Parsable.sendTo(client: Session) {
    saveInvoke{
        synchronized (client) {
            client.basicRemote.sendObject(this)
        }
    }
}