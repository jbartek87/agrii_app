package com.jbartek.agrii.controller;

import com.jbartek.agrii.lorApi.LorApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class LorApiController {
    private LorApi api;

    @Autowired
    public LorApiController(LorApi api) {
        this.api = api;
    }

    @GetMapping("/news")
    public String getContent() {
        return api.getLoripsum();
    }
}
