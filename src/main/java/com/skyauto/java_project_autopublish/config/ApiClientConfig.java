package com.skyauto.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * API 클라이언트 설정
 * - Claude API (콘텐츠 생성용)
 * - Naver Blog API (블로그 업로드용)
 */
@Configuration      // ← Spring이 설정 클래스로 인식
public class ApiClientConfig {

    /**
     * Claude API WebClient
     *
     * 인증 방식:
     * - x-api-key 헤더에 API 키 포함
     * - anthropic-version 헤더 필수
     *
     * @param baseUrl Claude API 기본 URL
     * @param apiKey Claude API 키
     * @param timeout 타임아웃 (밀리초)
     * @return Claude API 호출용 WebClient
     */
    @Bean
    @Qualifier("claude")
    public WebClient claudeWebClient(
            @Value("${api.claude.base-url}") String baseUrl,
            @Value("${api.claude.api-key}") String apiKey,
            @Value("${api.claude.timeout}") int timeout
    ) {
        // HTTP 클라이언트 타임아웃 설정
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                .responseTimeout(Duration.ofMillis(timeout))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(timeout, TimeUnit.MILLISECONDS))
                );

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-api-key", apiKey)
                .defaultHeader("anthropic-version", "2023-06-01")
                .defaultHeader("Content-Type", "application/json")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(10 * 1024 * 1024)) // 10MB
                .build();
    }

    /**
     * Naver Blog API WebClient
     *
     * 인증 방식:
     * - X-Naver-Client-Id 헤더
     * - X-Naver-Client-Secret 헤더
     *
     * @param clientId Naver Client ID
     * @param clientSecret Naver Client Secret
     * @return Naver Blog API 호출용 WebClient
     */
    @Bean  // ← Spring Container에 등록
    @Qualifier("naver")  // ← 이름 지정
    public WebClient naverWebClient(
            @Value("${api.naver.client-id}") String clientId,
            @Value("${api.naver.client-secret}") String clientSecret
    ) {
        return WebClient.builder()
                .baseUrl("https://openapi.naver.com")
                .defaultHeader("X-Naver-Client-Id", clientId)
                .defaultHeader("X-Naver-Client-Secret", clientSecret)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}