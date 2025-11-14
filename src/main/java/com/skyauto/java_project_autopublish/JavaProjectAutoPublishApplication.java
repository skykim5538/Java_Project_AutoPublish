package com.skyauto.java_project_autopublish;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * AI 에이전트 기반 콘텐츠 자동화 시스템
 *
 * 기능:
 * 1. 트렌드 키워드 추출
 * 2. 상품 정보 수집
 * 3. LLM 콘텐츠 생성
 * 4. 블로그 자동 업로드
 * 5. 매일 08:00 자동 실행
 */

@SpringBootApplication

public class JavaProjectAutoPublishApplication {
    public static void main(String[] args) {

        SpringApplication.run(JavaProjectAutoPublishApplication.class, args);
    }
}

