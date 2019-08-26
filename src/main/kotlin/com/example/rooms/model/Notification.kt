package com.example.rooms.model

import javax.websocket.Session

abstract class Notification (val event: Event): Parsable {
    enum class Event {
        VISITOR_IN, LIGHT_STATE_CHANGED, VISITOR_OUT
    }
}

class VisitorInNotification (val visitorId: String): Notification (Event.VISITOR_IN)
class VisitorOutNotification (val visitorId: String): Notification (Event.VISITOR_OUT)
class ChangedLightStateNotification(val isLightOn: Boolean, val initiatorId: String): Notification (Event.LIGHT_STATE_CHANGED)