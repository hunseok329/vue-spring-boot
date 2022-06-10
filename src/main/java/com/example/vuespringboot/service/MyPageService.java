package com.example.vuespringboot.service;

import com.example.vuespringboot.dao.NoticeRepository;
import com.example.vuespringboot.entity.NoticeList;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyPageService {
    private NoticeRepository noticeRepository;

    public MyPageService (NoticeRepository noticeRepository) { this.noticeRepository = noticeRepository; }

    public ArrayList<String> getNoticeList(String username) {
        ArrayList<String> getNoticeItems = new ArrayList<>();

        List<NoticeList> noticeLists = noticeRepository.findByUserId(username);

        for(NoticeList noticeItem : noticeLists) {
            getNoticeItems.add(noticeItem.getNoticeIndex());
        }

        return getNoticeItems;
    }

    public void modifyMyPage(HttpServletRequest request) {
        String username = request.getParameter("username");
        String noticeList = request.getParameter("noticeList");

        ArrayList<String> noticeArrayList = new ArrayList<>();

        if (!noticeList.isEmpty()){
            long cnt = countChar(noticeList, ',');
            if(cnt == 0){
                //noticeList item is 1
                noticeArrayList.add(noticeList);

            } else {
                //noticeList items
                String[] arrList = noticeList.split(",");
                for (String arr : arrList) {
                    noticeArrayList.add(arr);
                }
            }

        } else {
            //noticeList is null
        }

        //database update
        deleteData(username, noticeArrayList);
        insertData(username, noticeArrayList);
    }

    public static long countChar(String str, char ch) {
        return str.chars()
                .filter(c -> c == ch)
                .count();
    }

    public void deleteData(String username, ArrayList<String> noticeArrayList) {
        List<NoticeList> noticeLists = noticeRepository.findByUserId(username);

        for(NoticeList noticeItem : noticeLists) {
            if(!Arrays.asList(noticeArrayList).contains(noticeItem.getNoticeIndex())) {
                noticeRepository.deleteById(noticeItem.getId());
            }

        }
    }

    public void insertData(String username, ArrayList<String> noticeArrayList){
        ArrayList<String> noticeList = getNoticeList(username);
        for(String noticeItem : noticeArrayList) {
            if(!Arrays.asList(noticeList).contains(noticeItem)){
                String index_url = SignupUserService.getURL(noticeItem);
                NoticeList notice = new NoticeList(username, noticeItem, index_url);
                noticeRepository.save(notice);
            }
        }
    }

}
