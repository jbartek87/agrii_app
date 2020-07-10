package com.jbartek.agrii.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OwApiConfig {

    @Value("${ow.api.endpoint}")
    private String owApiEndpoint;
}
