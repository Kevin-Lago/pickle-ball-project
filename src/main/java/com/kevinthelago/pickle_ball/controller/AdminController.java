package com.kevinthelago.pickle_ball.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinthelago.pickle_ball.constants.iso_codes.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public List<String> getConfiguredCountries() {
        return Arrays.stream(Country.values()).map(country -> {
            try {
                return objectMapper.writeValueAsString(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(Country.BRAZIL.toMap()));
            } catch (JsonProcessingException e) {
                return null;
            }
        }).collect(Collectors.toList());
    }
}
