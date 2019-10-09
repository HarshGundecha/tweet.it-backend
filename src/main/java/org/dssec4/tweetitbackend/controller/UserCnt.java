package org.dssec4.tweetitbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserCnt {
    @GetMapping("/")
    public String Test()
    {
        return "hello";
    }
}
