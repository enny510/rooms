package com.example.rooms.utils

import com.example.rooms.RoomsApplicationTests
import com.example.rooms.model.*
import org.junit.Assert
import java.net.URI
import javax.websocket.ContainerProvider
import javax.websocket.Session

inline fun withSession(action: () -> Unit) {
    val wsContainer = ContainerProvider.getWebSocketContainer()
    val clientEndpoint = TesterClientEndpoint()
    val wsSession = wsContainer.connectToServer(
            clientEndpoint,
            URI(RoomsApplicationTests.URI + Room("testRoom").name))
    action.invoke()

    wsSession.close()
}