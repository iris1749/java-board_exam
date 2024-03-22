package org.example;
import  java.time.LocalDateTime;

public class Article {
    private int id; // 번호
    private String title; // 제목

    private String detail; // 내용

    private int hit; // 조회수

    private String regDate; // 등록일

    public Article(int id, String title, String detail, int hit, String regDate) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.regDate = regDate;
    }

    public void increaseHit() {
        this.hit++;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}