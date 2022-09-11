package com.stephen.jenkinsdockerkubernetesgke.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String welcome(){
        return "welcome to Jenkins,Docker,Kubernetes and GKE";
    }
}
