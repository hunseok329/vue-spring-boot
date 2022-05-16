package com.example.vuespringboot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CrawlingService {

    public ArrayList<HashMap<String, String>> getNotice() throws IOException {
        // parsing data list
        ArrayList<HashMap<String, String>> noticeItems = new ArrayList<HashMap<String, String>>();
        // jsoup test code
        String mainURL = "https://ce.daegu.ac.kr/hakgwa_home/ce/sub.php?menu=page&menu_id=30";
        Document doc = Jsoup.connect(mainURL).get();

        Elements parsingTable = doc.getElementsByClass("board_list");
        Elements parsingTbody = parsingTable.select("tbody");
        Elements parsingTr = parsingTbody.select("tr");


        for(Element trItem : parsingTr){
            HashMap<String, String> noticeItem = new HashMap<String, String>();

            Elements th = trItem.select("th");

//          <th> 공지 태그 판별 조건문
            if(!th.isEmpty()){
//                test code
//                System.out.println("공지 게시글 입니다.");

                Elements tdItems = trItem.select("td");

                String noticeTitle = tdItems.get(0).text();
                String noticeAuthor = tdItems.get(1).text();
                String noticeDate = tdItems.get(2).text();

//               hashMap 생성
                noticeItem.put("head", "공지");
                noticeItem.put("title", noticeTitle);
                noticeItem.put("author", noticeAuthor);
                noticeItem.put("date", noticeDate);

//                test code
//                System.out.println("공지 게시글 제목 : " + noticeTitle);
//                System.out.println("공지 게시글 저자 : " + noticeAuthor);
//                System.out.println("공지 게시 날짜 : " + noticeDate);
            } else {
//                test code
//                System.out.println("일반 게시글 입니다.");

                Elements tdItems = trItem.select("td");

                String num = tdItems.get(0).text();
                String title = tdItems.get(1).text();
                String author = tdItems.get(2).text();
                String date = tdItems.get(3).text();

//                hashMap 생성
                noticeItem.put("head", num);
                noticeItem.put("title", title);
                noticeItem.put("author", author);
                noticeItem.put("date", date);

//                test code
//                System.out.println("게시글 번호 : " + num);
//                System.out.println("게시을 제목 : " + title);
//                System.out.println("게시글 저자 : " + author);
//                System.out.println("게시 날짜 : " + date);
            }

            //각 게시글 정보를 noticeItems 배열에 추가
            noticeItems.add(noticeItem);
        }
        return noticeItems;
    }
}
