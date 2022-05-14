package org.duckdns.reimu.memoria.controller.music

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/music")
class HomeController {
    @GetMapping
    fun home(model: Model): String {
        model.addAttribute("title", "Home")
        return "music/home"
    }
}
