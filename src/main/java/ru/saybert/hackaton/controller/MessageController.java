package ru.saybert.hackaton.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;

@RestController
@RequestMapping("/message")
public class MessageController {

//
//    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String classifyByCategory (@RequestParam("message") String message) {
//
//    }
//
//    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String classifyByRelevance (@RequestParam("message") String message) {
//
//    }
//
//    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String assignToEmployee (@RequestParam("message") String message) {
//
//    }
}
