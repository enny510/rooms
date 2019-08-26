package com.example.rooms.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Value

abstract class Response(@JsonProperty val event: Event): Parsable {
    enum class Event {
        CONNECTED, LIGHT_STATE_CHANGED, ERROR
    }
}

class ConnectionResponse(@JsonProperty("lightOn") val isLightOn: Boolean, @JsonProperty val visitorsCount: Int): Response (Event.CONNECTED)
class ChangedLightStateResponse(@JsonProperty("lightOn") val isLightOn: Boolean): Response (Event.LIGHT_STATE_CHANGED)
class ErrorResponse(val errorText: String): Response(Event.ERROR)
