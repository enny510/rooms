package com.example.rooms.utils

import com.example.rooms.model.ChangedLightStateResponse
import com.example.rooms.model.ConnectionResponse
import com.example.rooms.model.Parsable
import com.example.rooms.model.Response
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import javax.websocket.Decoder
import javax.websocket.EncodeException
import javax.websocket.Encoder
import javax.websocket.EndpointConfig

class TesterMessageDecoder : Decoder.Text<Parsable> {

    @Throws(EncodeException::class)
    override fun decode(s: String?): Parsable? {
        val mapper = ObjectMapper().registerModule(KotlinModule())
        return try {
            System.out.println(s)

            val obj = when(Regex("""\"event\":\"(\w+)\"""").find(s!!.subSequence(0, s.length))?.groups?.get(1)?.value?:"") {
                Response.Event.CONNECTED.name -> mapper.readValue(s, ConnectionResponse::class.java)
                Response.Event.LIGHT_STATE_CHANGED.name -> mapper.readValue(s, ChangedLightStateResponse::class.java)
                else -> TODO()
            }

            obj
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
            null
        }
    }

    override fun willDecode(s: String?): Boolean {
        return s != null
    }

    override fun init(endpointConfig: EndpointConfig) {
    }

    override fun destroy() {
    }
}