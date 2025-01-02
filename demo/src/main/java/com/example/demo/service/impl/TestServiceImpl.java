package com.example.demo.service.impl;

import com.example.demo.dto.TestDTO;
import com.example.demo.service.TestService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class TestServiceImpl implements TestService {

    Logger log = LoggerFactory.getLogger(TestServiceImpl.class);

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Autowired
    private OkHttpClient okHttpClient;

    @Override
    public List<TestDTO.Response> getAll() {
        List<TestDTO.Response> responses = new ArrayList<>();

        Request httpRequest = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .build();

        try (Response httpResponse = okHttpClient.newCall(httpRequest).execute()) {
            if (httpResponse.code() == 404) throw new IOException("Not found: "+httpResponse);
            String responseBody = Objects.requireNonNull(httpResponse.body()).string();
            ArrayNode jsonArr = objectMapper.convertValue(responseBody, new TypeReference<>() {});

            for (JsonNode json : jsonArr) {
                TestDTO.Response response = new TestDTO.Response();

                response.userId = json.findPath("userId").asInt(0);
                response.id = json.findPath("id").asInt(0);
                response.title = json.findPath("title").asText("");
                response.body = json.findPath("body").asText("");

                responses.add(response);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return responses;
    }

    @Override
    public TestDTO.Response getById(TestDTO.Request request) {
        TestDTO.Response response = new TestDTO.Response();

        Request httpRequest = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .build();

        try (Response httpResponse = okHttpClient.newCall(httpRequest).execute()) {
            if (httpResponse.code() == 404) throw new IOException("Not found: "+httpResponse);
            String responseBody = Objects.requireNonNull(httpResponse.body()).string();
            ArrayNode jsonArr = objectMapper.convertValue(responseBody, new TypeReference<>() {});
            List<TestDTO.Response> responses = Stream.of(objectMapper.convertValue(jsonArr, TestDTO.Response.class))
                    .filter(x -> x.id.equals(request.id)).toList();

            response = responses.getFirst();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return response;
    }
}
