package com.example.demo.controller;

import com.example.demo.dto.TestDTO;
import com.example.demo.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    TestService testService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TestDTO.Response>> getAll() {
        List<TestDTO.Response> response = testService.getAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getById")
    public ResponseEntity<TestDTO.Response> getById(@RequestBody TestDTO.Request request) {
        TestDTO.Response response = testService.getById(request);
        return ResponseEntity.ok(response);
    }
}
