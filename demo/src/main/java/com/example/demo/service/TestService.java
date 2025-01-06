package com.example.demo.service;

import com.example.demo.dto.TestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestService {

    Page<TestDTO.Response> getAll(Pageable pageable);
    TestDTO.Response getById(TestDTO.Request request);
}
