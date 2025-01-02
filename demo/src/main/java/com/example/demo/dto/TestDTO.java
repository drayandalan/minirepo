package com.example.demo.dto;

public class TestDTO {

    public static class Request {
        public Integer id;

    }

    public static class Response {
        public Integer userId;
        public Integer id;
        public String title;
        public String body;
        public String error;
    }
}
