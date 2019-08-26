package com.example.rooms.utils

import com.example.rooms.model.Parsable
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.TimeUnit
import javax.websocket.ClientEndpoint
import javax.websocket.OnMessage
import javax.websocket.Session

@ClientEndpoint(decoders = [TesterMessageDecoder::class])
class TesterClientEndpoint {

    private val messages: BlockingQueue<Parsable> =  LinkedBlockingDeque()

    @OnMessage
    fun onMessage(session: Session, message: Parsable) {
        messages.offer(message)
    }

    fun getMessage(): Parsable? {
        return messages.poll(1, TimeUnit.SECONDS)
    }
}