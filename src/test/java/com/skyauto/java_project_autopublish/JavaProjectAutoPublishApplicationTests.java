package com.skyauto.java_project_autopublish;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.ai.openai.api-key=test-key",
        "api.claude.api-key=test-key",
        "api.naver.client-id=test-id",
        "api.naver.client-secret=test-secret"
})
class JavaProjectAutoPublishApplicationTests {

    @Test
    void contextLoads() {
        // Spring Context 로딩만 확인
    }
}