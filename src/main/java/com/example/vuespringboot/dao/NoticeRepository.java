package com.example.vuespringboot.dao;

import com.example.vuespringboot.entity.NoticeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<NoticeList, Long> {
    List<NoticeList> findByUserId(String userId);

    void deleteByUserId(String username);
}
