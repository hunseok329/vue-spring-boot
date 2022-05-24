package com.example.vuespringboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "notice_list")
public class NoticeList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, name = "user_id")
    private String userId;

    @Column(name = "notice_index")
    private String noticeIndex;
    @Column(name = "notice_url")
    private String noticeUrl;

    public NoticeList(String userId, String noticeIndex, String noticeUrl ) {
        this.userId = userId;
        this.noticeIndex = noticeIndex;
        this.noticeUrl = noticeUrl;
    }

    protected NoticeList() {

    }
}
