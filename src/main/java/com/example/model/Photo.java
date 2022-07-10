package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public record Photo(@JsonProperty("img_src") String imgSrc, Integer length, URI location) {
}
