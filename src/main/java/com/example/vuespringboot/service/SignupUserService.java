package com.example.vuespringboot.service;

import com.example.vuespringboot.dao.UserRepository;
import com.example.vuespringboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.ArrayList;
@Service
public class SignupUserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public SignupUserService(UserRepository userRepository) { this.userRepository = userRepository; }
    public void SignupUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String noticeList = request.getParameter("noticeList");

        ArrayList<String> noticeArrayList = new ArrayList<String>();
        password = passwordEncoder.encode(password);

        System.out.println("this is user ud : " + username);
        System.out.println("this, is user password : " + password);
        System.out.println("this is noticeLsit : " + noticeList);
        System.out.println(noticeList.getClass().getName());

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
                System.out.println("Array list ");
                System.out.println(noticeArrayList);
            }

        } else {
            //noticeList is null
            System.out.println(noticeArrayList);
        }

        //테이블에 데이터 입력
        //user table save
        User user = new User(username, password, "USER", "");
        userRepository.save(user);

        //notice_list table save

    }

    public static long countChar(String str, char ch) {
        return str.chars()
                .filter(c -> c == ch)
                .count();
    }
}
