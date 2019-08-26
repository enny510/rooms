package com.example.rooms.endpoints

import com.example.rooms.model.*
import com.example.rooms.model.extensions.broadcastFor
import com.example.rooms.model.extensions.sendTo
import java.io.IOException
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint


@ServerEndpoint(
        value = "/room/{roomName}",
        encoders = [ParsableEncoder::class]
)
class RoomEndpoint {

    private lateinit var session: Session

    @OnOpen
    @Throws(IOException::class)
    fun onOpen(session: Session, @PathParam("roomName") roomName: String ) {
        RoomsSource.rooms.find { it.name == roomName }?.let {
            ConnectionResponse(it.isLightOn, it.visitors.size + 1).sendTo(session)
            VisitorInNotification(session.id).broadcastFor(it.visitors)
            it.visitors = it.visitors.plus(session)
        }
    }

    @OnMessage
    @Throws(IOException::class)
    fun onMessage(session: Session, @PathParam("roomName") roomName: String, message: String) {
        RoomsSource.rooms.find { it.name == roomName }?.let {
            it.isLightOn = !it.isLightOn
            ChangedLightStateResponse(it.isLightOn).sendTo(session)
            ChangedLightStateNotification(it.isLightOn, session.id).broadcastFor(it.visitors.minus(session))
        }
    }

    @OnClose
    @Throws(IOException::class)
    fun onClose(session: Session, @PathParam("roomName") roomName: String) {
        RoomsSource.rooms.find { it.name == roomName }?.let {
            it.visitors = it.visitors.minus(session)
            VisitorOutNotification(session.id).broadcastFor(it.visitors)
        }
    }

    @OnError
    fun onError(session: Session, throwable: Throwable) {
        if (throwable is DecodeException) {
            ErrorResponse("Unable to read you message").sendTo(session)
        }
        throwable.printStackTrace()
    }
}