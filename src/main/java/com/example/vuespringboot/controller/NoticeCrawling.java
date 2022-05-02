package com.example.vuespringboot.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.vuespringboot.service.CrawlingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
}
