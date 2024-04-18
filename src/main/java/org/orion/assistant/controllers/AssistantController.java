package org.orion.assistant.controllers;

import org.orion.assistant.persistence.models.User;
import org.orion.assistant.persistence.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssistantController {

    @GetMapping("/test")
    public String test() {
        processService.process(new User("name", "mail", "null", "null"));
        return "API test";
    }


    private ProcessService processService;

    @Autowired
    public AssistantController(ProcessService processService) {
        this.processService = processService;
    }
}
