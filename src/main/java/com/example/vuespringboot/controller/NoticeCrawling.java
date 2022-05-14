package com.example.vuespringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.vuespringboot.service.CrawlingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class NoticeCrawling {

    @Autowired
    private CrawlingService crawlingService;
    @GetMapping("/hello")
    public ArrayList<HashMap<String, String>> noticeCrawling() throws IOException {
        //크롤링 결과 리스트
        ArrayList<HashMap<String, String>> notice = crawlingService.getNotice();

        return notice;
    }

    final  private static Logger LOG = Logger.getGlobal();
    public static final String SECURED_TEXT = "Hello from the secured resource!";

    @GetMapping("/login")
    public void login() {
        LOG.info("GET sucessfully called on /login resource");
    }
}
