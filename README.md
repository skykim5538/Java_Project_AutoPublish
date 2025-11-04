# 🤖 AI 에이전트 기반 콘텐츠 자동화 시스템

> **기업 연계 프로젝트 리팩토링**

트렌드 상품 분석부터 콘텐츠 생성, 블로그 자동 배포까지 - 전 과정을 AI가 자동화하는 마케팅 솔루션

[![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Oracle](https://img.shields.io/badge/Oracle-19c-F80000?style=flat&logo=oracle&logoColor=white)](https://www.oracle.com/database/)
[![AWS](https://img.shields.io/badge/AWS-Deploy-FF9900?style=flat&logo=amazon-aws&logoColor=white)](https://aws.amazon.com/)

---

## 📋 목차
- [프로젝트 개요](#-프로젝트-개요)
- [주요 기능](#-주요-기능)
- [기술 스택](#-기술-스택)
- [시스템 아키텍처](#-시스템-아키텍처)
- [프로젝트 구조](#-프로젝트-구조)
- [실행 방법](#-실행-방법)

---

## 🎯 프로젝트 개요

### 배경
커머스 업계는 AI 기반 마케팅 자동화 기술이 빠르게 확산되고 있지만, 여전히 낮은 콘텐츠 퀄리티와 플랫폼 통합의 어려움으로 인해 수작업이 필요한 상황입니다.

### 목표
**상품 수집 → 콘텐츠 생성 → 자동 배포**의 전 과정을 AI 에이전트가 수행하여
- ✅ 트래픽 증가
- ✅ 운영 효율성 향상
- ✅ 광고비 절감

---

## ⚙️ 주요 기능

### 1️⃣ 트렌드 상품 자동 수집
- **Google Trends**에서 실시간 이슈 상품 TOP 50 수집
- 랜덤 키워드 선택 알고리즘

### 2️⃣ 상품 정보 크롤링
- 쇼핑몰 접속  
- 키워드 기반 상품명, 상품 URL 자동 추출

### 3️⃣ AI 콘텐츠 생성
- **LLM(GPT/Gemini 등)** 기반 상품 홍보 콘텐츠 자동 생성
- SEO/AI 검색 최적화 적용

### 4️⃣ 블로그 자동 배포
- **블로그 API** 연동
- 생성된 콘텐츠 원클릭 업로드

### 5️⃣ 스케줄링 자동화
- 매일 **오전 08:00** 정기 실행
- Spring Scheduler 기반 무인 운영

---

## 🛠 기술 스택

### Backend
| 기술 | 버전 | 용도      |
|------|------|---------|
| **Java** | JDK 21 | 백엔드 언어  |
| **Spring Boot** | 3.x | 프레임워크   |
| **Maven** | - | 빌드 도구   |
| **MyBatis** | - | SQL Mapper    |
| **Spring Scheduler** | - | 작업 스케줄링 |

### Database
| 기술 | 용도 |
|------|------|
| **Oracle 19c** | 메인 데이터베이스 |
| **AWS RDS** | 클라우드 DB |

### AI & Crawling
| 기술                      | 용도 |
|-------------------------|------|
| **OpenAI GPT / Claude** | 콘텐츠 생성 LLM |
| **Jsoup**               | 웹 크롤링 |

### Infrastructure
| 기술 | 용도 |
|------|------|
| **AWS** | 클라우드 배포 |
| **IntelliJ IDEA** | 개발 IDE |

---

## 🏗 시스템 아키텍처

```
┌─────────────────┐
│  Trends         │ ──┐
└─────────────────┘   │
                      ▼
              ┌───────────────┐
              │ 키워드 추출    │
              │ (Random 선택)  │
              └───────────────┘
                      │
                      ▼
┌─────────────────┐   │   ┌─────────────────┐
│  ssadagu.kr     │◄──┴──►│  Spring Boot    │
│  (크롤링)       │       │  Backend        │
└─────────────────┘       └─────────────────┘
                                  │
                                  ▼
                          ┌───────────────┐
                          │  Oracle DB    │
                          │  (키워드/URL)  │
                          └───────────────┘
                                  │
                                  ▼
┌─────────────────┐       ┌───────────────┐
│  OpenAI GPT     │◄──────│  콘텐츠 생성   │
│  / Gemini       │       │  Service      │
└─────────────────┘       └───────────────┘
                                  │
                                  ▼
                          ┌───────────────┐
                          │  Oracle DB    │
                          │  (콘텐츠 저장) │
                          └───────────────┘
                                  │
                                  ▼
┌─────────────────┐       ┌───────────────┐
│  Naver Blog     │◄──────│  자동 업로드   │
│  API            │       │  Service      │
└─────────────────┘       └───────────────┘
         ▲
         │
    매일 08:00 실행
   (Spring Scheduler)
```

---

## 📁 프로젝트 구조

```
전체 구조 

├── src/                     
│   ├── main/
│   │   ├── java/
│   │   │   └── com/company/ssadagu/
│   │   │       ├── app/
│   │   │       └── domain/   
│   │   │             └── domainName/                                      
│   │	│	          ├── controller   
│   │	│		  ├── service
│   │	│		  ├── mapper 
│   │	│		  ├── dto 
│   │	│		  └── vo       
│   │   │
│   │   └── resources/
│   │       └── mappers/
│   │           └── productMapper.xml    
│   │
│   └── test/                 
│
├── build.gradle.kts         
├── settings.gradle.kts       
```

### DTO/VO 네이밍 규칙
- **RequestDTO / ResponseDTO**: UI ↔ Java 계층 통신
- **RequestVO / ResponseVO**: Java ↔ DB 계층 통신

---

## 🚀 실행 방법

### 1. 필수 요구사항
- JDK 21 이상
- Maven 3.8+
- Oracle Database 19c
- IntelliJ IDEA (권장)



### 3. 빌드 및 실행



---

## 📊 주요 프로세스

### 자동화 워크플로우
1. **08:00** - Spring Scheduler 작동
2. **Google Trends** - 상위 50개 키워드 수집
3. **랜덤 선택** - 1개 키워드 추출
4. **쇼핑몰 접속** - 키워드로 상품 검색 후 첫번째 상품 정보 수집
5. **LLM 생성** - 상품 URL 기반 홍보 콘텐츠 생성
6. **DB 저장** - 키워드, URL, 콘텐츠 저장
7. **블로그 업로드** - 네이버 블로그 자동 게시

---

## 📝 주요 특징

✨ **완전 자동화**: 사람의 개입 없이 24시간 무인 운영  
✨ **실시간 트렌드 반영**: 구글 트렌드 기반 이슈 상품 자동 추적  
✨ **AI 콘텐츠 생성**: LLM 기반 자연스러운 홍보글 작성  
✨ **SEO 최적화**: 검색 엔진 친화적 콘텐츠 구조  
✨ **확장 가능성**: 다른 쇼핑몰/블로그 플랫폼 연동 가능

---

## ⚠️ 주의사항



---