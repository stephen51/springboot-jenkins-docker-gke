package com.stephen.jenkinsdockerkubernetesgke.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String welcome(){
        return "welcome to Jenkins,Docker,Kubernetes and GKE";
    }

    @GetMapping("/test")
    public String test(){
        return "Github webhook is working fine";
    }
}
