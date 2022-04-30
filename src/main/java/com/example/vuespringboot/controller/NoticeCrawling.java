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

@RestController
@RequestMapping("/api")
public class NoticeCrawling {

    @Autowired
    private CrawlingService crawlingService;
    @GetMapping("/hello")
    public String noticeCrawling() throws IOException {

        String notice = crawlingService.getNotice();
        //        jsoup test code
        String mainURL = "https://ce.daegu.ac.kr/hakgwa_home/ce/sub.php?menu=page&menu_id=30";
        Document doc = Jsoup.connect(mainURL).get();

//        System.out.println(doc.text());

        Elements parsingTable = doc.getElementsByClass("board_list");
        Elements parsingTbody = parsingTable.select("tbody");
        Elements temp = parsingTbody.select("tr");
        System.out.println(temp);

//        for(Element item : parsingTbody){
//            System.out.println(item);
//        }

        System.out.println("<------------------------>");
        System.out.println("<------------------------>");
        System.out.println("<------------------------>");
        System.out.println("<------------------------>");
        System.out.println("<------------------------>");
//        System.out.println(parsingTable);

        return parsingTable.html();
    }
}
