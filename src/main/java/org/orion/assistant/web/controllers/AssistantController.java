package org.orion.assistant.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssistantController {


    @GetMapping("/test")
    public String test() {
        return "API test";
    }
}
