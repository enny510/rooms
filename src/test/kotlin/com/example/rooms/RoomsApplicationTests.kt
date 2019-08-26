package com.example.rooms

import com.example.rooms.endpoints.RoomEndpoint
import com.example.rooms.model.ChangedLightStateResponse
import com.example.rooms.model.ConnectionResponse
import com.example.rooms.model.Response
import com.example.rooms.model.RoomsSource
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI
import javax.websocket.ContainerProvider
import com.example.rooms.utils.TesterClientEndpoint
import com.example.rooms.utils.withSession


@RunWith(SpringRunner::class)
@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomsApplicationTests {

	companion object {
		const val URI = "ws://localhost:8080/room/"
	}

	@Test
	fun testConnection() {
		val wsContainer = ContainerProvider.getWebSocketContainer()
		val clientEndpoint = TesterClientEndpoint()
		val wsSession = wsContainer.connectToServer(
				clientEndpoint,
				URI(URI + RoomsSource.rooms[0].name))

		val message = clientEndpoint.getMessage() as? ConnectionResponse
		Assert.assertNotNull(message)
		Assert.assertEquals(Response.Event.CONNECTED, message!!.event);
		Assert.assertEquals(1, message.visitorsCount);
		wsSession.close()
	}

	@Test
	fun testSwitchRequest() {
		val wsContainer = ContainerProvider.getWebSocketContainer()
		val clientEndpoint = TesterClientEndpoint()
		val wsSession = wsContainer.connectToServer(
				clientEndpoint,
				URI(URI + RoomsSource.rooms[0].name))
		wsSession.basicRemote.sendText("")

		val connectionResponseMessage = clientEndpoint.getMessage() as? ConnectionResponse
		Assert.assertNotNull(connectionResponseMessage)
		val isLightOn = connectionResponseMessage!!.isLightOn

		val switchResponseMessage = clientEndpoint.getMessage() as? ChangedLightStateResponse
		Assert.assertEquals(Response.Event.LIGHT_STATE_CHANGED, switchResponseMessage!!.event);
		Assert.assertEquals(switchResponseMessage.isLightOn, !isLightOn)

		wsSession.close()
	}

//	@Test
//	fun testSwitchRequest() {
//		withSession {
//			wsSession.basicRemote.sendText("")
//
//			val connectionResponseMessage = clientEndpoint.getMessage() as? ConnectionResponse
//			Assert.assertNotNull(connectionResponseMessage)
//			val isLightOn = connectionResponseMessage!!.isLightOn
//
//			val switchResponseMessage = clientEndpoint.getMessage() as? ChangedLightStateResponse
//			Assert.assertEquals(Response.Event.LIGHT_STATE_CHANGED, switchResponseMessage!!.event);
//			Assert.assertEquals(switchResponseMessage.isLightOn, !isLightOn)
//		}
//
//	}

}
