package com.example.rooms.model

import com.example.rooms.saveInvoke
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import javax.websocket.EncodeException
import javax.websocket.Encoder
import javax.websocket.EndpointConfig
import javax.websocket.Session

interface Parsable

class ParsableEncoder : Encoder.Text<Parsable> {

    @Throws(EncodeException::class)
    override fun encode(response: Parsable): String {
        val mapper = ObjectMapper()
        return try {
            val json = mapper.writeValueAsString(response)
            println("JSON = $json")
            json
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
            ""
        }
    }

    override fun init(endpointConfig: EndpointConfig) {
    }

    override fun destroy() {
    }
}