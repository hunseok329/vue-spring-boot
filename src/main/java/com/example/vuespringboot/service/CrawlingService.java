package com.example.vuespringboot.service;

import com.example.vuespringboot.dao.NoticeRepository;
import com.example.vuespringboot.entity.NoticeList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CrawlingService {
    private final NoticeRepository noticeRepository;

    public CrawlingService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public ArrayList<HashMap<String, String>> getNotice(String username) throws IOException {
        // parsing data list
        ArrayList<HashMap<String, String>> noticeItems = new ArrayList<HashMap<String, String>>();

        // db data get
        List<NoticeList> noticeLists = noticeRepository.findByUserId(username);

        for(NoticeList item : noticeLists) {
            if (item.getNoticeIndex().equals("학사공지")) {
                ArrayList<HashMap<String, String>> temps = crawlingBachelor(item.getNoticeUrl());
                for (HashMap<String, String> temp : temps) {
                    noticeItems.add(temp);
                }
            } else {
                ArrayList<HashMap<String, String>> temps = crawlingDepart(item.getNoticeUrl());
                for (HashMap<String, String> temp : temps) {
                    noticeItems.add(temp);
                }
            }
        }
        return noticeItems;
    }



    public ArrayList<HashMap<String, String>> crawlingDepart(String url) throws IOException{
        ArrayList<HashMap<String, String>> tempList = new ArrayList<HashMap<String, String>>();

        // jsoup test code
        String mainURL = url;
        Document doc = Jsoup.connect(mainURL).get();

        Elements parsingTable = doc.getElementsByClass("board_list");
        Elements parsingTbody = parsingTable.select("tbody");
        Elements parsingTr = parsingTbody.select("tr");


        for (Element trItem : parsingTr) {
            HashMap<String, String> noticeItem = new HashMap<String, String>();

            Elements th = trItem.select("th");

//          <th> 공지 태그 판별 조건문
            if (!th.isEmpty()) {
                //공지
                Elements tdItems = trItem.select("td");

                String noticeTitle = tdItems.get(0).text();
                String noticeAuthor = tdItems.get(1).text();
                String noticeDate = tdItems.get(2).text();

//               hashMap 생성
                noticeItem.put("head", "공지");
                noticeItem.put("title", noticeTitle);
                noticeItem.put("author", noticeAuthor);
                noticeItem.put("date", noticeDate);

            } else {
                //일반

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

            }

            //각 게시글 정보를 noticeItems 배열에 추가
            tempList.add(noticeItem);
        }
        return tempList;
    }

    public ArrayList<HashMap<String, String>> crawlingBachelor(String url) throws IOException {
        ArrayList<HashMap<String, String>> tempList = new ArrayList<HashMap<String, String>>();

        String mainURL = url;
        Document doc = Jsoup.connect(mainURL).get();

        Elements parsingTable = doc.getElementsByClass("board_tbl_list");
        Elements parsingTbody = parsingTable.select("tbody");
        Elements parsingTr = parsingTbody.select("tr");

        for (Element trItem : parsingTr ) {
            HashMap<String, String> noticeItem = new HashMap<String, String>();

            Elements tdItems = trItem.select("td");

            //공지사항 이미지 체크
            String x = tdItems.get(0).text();
            String noticeTag = tdItems.get(1).text();
            String noticeTitle = tdItems.get(2).text();
            String noticeAuthor = tdItems.get(3).text();
            String noticeDate = tdItems.get(4).text();

            if(x.isEmpty()) {
                //공지
                noticeItem.put("head", "공지");
                noticeItem.put("title", noticeTitle);
                noticeItem.put("author", noticeAuthor);
                noticeItem.put("date", noticeDate);
            } else {
                //일반
                noticeItem.put("head", x);
                noticeItem.put("title", noticeTitle);
                noticeItem.put("author", noticeAuthor);
                noticeItem.put("date", noticeDate);
            }
            tempList.add(noticeItem);
        }

        return tempList;
    }
}
