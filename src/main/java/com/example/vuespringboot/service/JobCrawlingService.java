package com.example.vuespringboot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class JobCrawlingService {
    public static HashMap<String, String> joblist(String job_name) throws IOException {
        HashMap<String, String>jobItem= new HashMap<>();
        String url = "https://www.career.go.kr/cnet/front/base/job/jobView.do?SEQ=1096";
        Document doc = null;
        Elements tmp;
        String title = null;
        String a = null;
        String b = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element jobtable  = doc.getElementById("tab1");
        Elements jobbody = jobtable.getElementsByClass("word_txt");
        Elements job1 = jobtable.select("table");
        Elements job2 = jobtable.select("ul");
        a = jobbody.text();
        b = job1.text();
        b = b.substring(15);
        String ul1 = job2.get(0).text();
        String ul2 = job2.get(1).text();


        jobItem.put("job", a);
        jobItem.put("department", b);
        jobItem.put("working", ul1);
        jobItem.put("working2", ul2);
        System.out.println(b.substring(15));
        return jobItem;
    }
}
