package com.example.rooms.controllers

import com.example.rooms.model.RoomsSource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("rooms", RoomsSource.rooms.map{it.name})
        return "home"
    }
}