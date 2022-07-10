package com.example.controller;

import com.example.helper.UriBuilderHelper;
import com.example.model.ImageAttribute;
import com.example.service.PicturesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/pictures")
@RequiredArgsConstructor
public class PicturesController {

    private final UriBuilderHelper uriBuilderHelper;
    private final RestTemplate restTemplate;
    private final PicturesService picturesService;

    @GetMapping("/{sol}/largest")
    public ResponseEntity<String> getLargestPicture(@PathVariable Integer sol) {
        String url = uriBuilderHelper.buildUrl(sol);
        ImageAttribute largest = picturesService.getLargestImageLength(url);
        return restTemplate.getForEntity(largest.imgSrc(), String.class);
    }

}

