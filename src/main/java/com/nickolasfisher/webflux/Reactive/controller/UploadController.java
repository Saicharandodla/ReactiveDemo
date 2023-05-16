package com.nickolasfisher.webflux.Reactive.controller;


import com.nickolasfisher.webflux.Reactive.service.ReactiveUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
public class UploadController {

    /*https://github.com/eugenp/tutorials/blob/master/spring-reactive-modules/spring-5-reactive-client/src/main/java/com/baeldung/reactive/controller/UploadController.java */

    final ReactiveUploadService uploadService;

    public UploadController(ReactiveUploadService uploadService){
this.uploadService = uploadService;

    }

    @PostMapping(path="/upload")
    @ResponseBody
    public Mono<Object> uploadPdf (@RequestParam("file") final MultipartFile multipartFile){
        return uploadService.uploadPdf(multipartFile.getResource());
    }

    @PostMapping(path="/upload/multipart")
    @ResponseBody
    public Mono<Object> uploadMultipart(@RequestParam("file") final MultipartFile multipartFile){
        return uploadService.uploadMultipart(multipartFile);
    }
}
