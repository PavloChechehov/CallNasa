package com.example.service;

import com.example.model.ImageAttribute;
import com.example.model.Photo;
import com.example.model.PhotoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Comparator;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PicturesService {

    private final RestTemplate restTemplate;

    public ImageAttribute getLargestImageLength(String url) {
        PhotoResponse response = restTemplate.getForObject(url, PhotoResponse.class);
        ImageAttribute largest = response.photos()
            .parallelStream()
            .map(Photo::imgSrc)
            .map(this::getImgAttribute)
            .max(Comparator.comparing(ImageAttribute::size))
            .orElseThrow(() -> new NoSuchElementException("No one photo exists"));

        log.info("Image url  = {}", largest.imgSrc());
        log.info("Image size = {} bytes", largest.size());
        return largest;
    }


    private ImageAttribute getImgAttribute(String img) {
        URI location = restTemplate.headForHeaders(img).getLocation();
        long length = restTemplate.headForHeaders(location).getContentLength();
        return new ImageAttribute(img, length);
    }
}
