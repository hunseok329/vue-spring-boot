package com.example.vuespringboot.service;

import com.example.vuespringboot.dao.NoticeRepository;
import com.example.vuespringboot.entity.NoticeList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CrawlingService {
    private final NoticeRepository noticeRepository;

    public CrawlingService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public ArrayList<HashMap<String, String>> getNotice(String username) throws IOException {
        // parsing data list
        ArrayList<HashMap<String, String>> noticeItems = new ArrayList<HashMap<String, String>>();

        //공지 게시글 임시 저장
        ArrayList<HashMap<String, String>> noticeBoard = new ArrayList<HashMap<String, String>>();

        //일반 게시글 임시 저장
        ArrayList<HashMap<String, String>> normalBoard = new ArrayList<HashMap<String, String>>();

        // db data get
        List<NoticeList> noticeLists = noticeRepository.findByUserId(username);

        for(NoticeList item : noticeLists) {
            //학사 공지는 HTML구조가 달라서 따로 처리
            if (item.getNoticeIndex().equals("학사공지")) {
                ArrayList<HashMap<String, String>> temps = crawlingBachelor(item.getNoticeUrl(), item.getNoticeIndex());
                for (HashMap<String, String> temp : temps) {
                    //공지글과 일반글 정렬 처리를 위해 구분 조건문
                    if(temp.get("head").equals("공지")) {
                        noticeBoard.add(temp);
                    } else {
                        normalBoard.add(temp);
                    }
                }
            } else {
                ArrayList<HashMap<String, String>> temps = crawlingDepart(item.getNoticeUrl(), item.getNoticeIndex());
                for (HashMap<String, String> temp : temps) {
                    //공지글과 일반글 정렬 처리를 위해 구분 조건문
                    if(temp.get("head").equals("공지")) {
                        noticeBoard.add(temp);
                    } else {
                        normalBoard.add(temp);
                    }
                }
            }
        }

        // noticeItems 배열 정리 함수
        //공지글 String date 기준 내림차순 정렬
        Collections.sort(noticeBoard, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                return o2.get("date").compareTo(o1.get("date"));
            }
        });

        //일반 게시글 String date 기준 내림차순 정렬
        Collections.sort(normalBoard, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                return o2.get("date").compareTo(o1.get("date"));
            }
        });
        noticeItems.addAll(noticeBoard);
        noticeItems.addAll(normalBoard);


        return noticeItems;
    }

    //학사공지용 크롤링 처리
    public ArrayList<HashMap<String, String>> crawlingBachelor(String url, String category) throws IOException {
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

            // Date 값을 yy/MM/dd로 설정
            noticeDate = noticeDate.replaceAll("-", "/").substring(2);

            if(x.isEmpty()) {
                //공지
                noticeItem.put("head", "공지");
                noticeItem.put("title", noticeTitle);
                noticeItem.put("category", category);
                noticeItem.put("author", noticeAuthor);
                noticeItem.put("date", noticeDate);
            } else {
                //일반
                noticeItem.put("head", x);
                noticeItem.put("title", noticeTitle);
                noticeItem.put("category", category);
                noticeItem.put("author", noticeAuthor);
                noticeItem.put("date", noticeDate);
            }
            tempList.add(noticeItem);
        }

        return tempList;
    }

    //학과 게시판 크롤링 기능
    public ArrayList<HashMap<String, String>> crawlingDepart(String url, String category) throws IOException{
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
                noticeItem.put("category", category);
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
                noticeItem.put("category", category);
                noticeItem.put("author", author);
                noticeItem.put("date", date);
            }

            //각 게시글 정보를 noticeItems 배열에 추가
            tempList.add(noticeItem);
        }
        return tempList;
    }
}
