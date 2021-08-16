package com.example.korarmy;

// 게시판 데이터
public class BoardData {

    private String tv_title;
    private String tv_ctx;
    private String tv_time;

    public BoardData(String tv_title, String tv_ctx, String tv_time) {
        this.tv_title = tv_title;
        this.tv_ctx = tv_ctx;
        this.tv_time = tv_time;
    }

    public String getTv_title() {
        return tv_title;
    }

    public void setTv_title(String tv_title) {
        this.tv_title = tv_title;
    }

    public String getTv_ctx() {
        return tv_ctx;
    }

    public void setTv_ctx(String tv_ctx) {
        this.tv_ctx = tv_ctx;
    }

    public String getTv_time() {
        return tv_time;
    }

    public void setTv_time(String tv_time) {
        this.tv_time = tv_time;
    }
}
