package com.example.demo.service;

import com.example.demo.dto.TestDTO;

import java.util.List;

public interface TestService {

    List<TestDTO.Response> getAll();
    TestDTO.Response getById(TestDTO.Request request);
}
