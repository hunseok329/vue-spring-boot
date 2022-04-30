package com.example.vuespringboot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CrawlingService {
    public String getNotice() throws IOException {
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

        return "";
    }
}
