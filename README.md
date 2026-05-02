<div align="center">

```
███████╗███╗   ███╗ █████╗ ██████╗ ████████╗     ██████╗ █████╗ ███╗   ███╗██████╗ ██╗   ██╗███████╗
██╔════╝████╗ ████║██╔══██╗██╔══██╗╚══██╔══╝    ██╔════╝██╔══██╗████╗ ████║██╔══██╗██║   ██║██╔════╝
███████╗██╔████╔██║███████║██████╔╝   ██║       ██║     ███████║██╔████╔██║██████╔╝██║   ██║███████╗
╚════██║██║╚██╔╝██║██╔══██║██╔══██╗   ██║       ██║     ██╔══██║██║╚██╔╝██║██╔═══╝ ██║   ██║╚════██║
███████║██║ ╚═╝ ██║██║  ██║██║  ██║   ██║       ╚██████╗██║  ██║██║ ╚═╝ ██║██║     ╚██████╔╝███████║
╚══════╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝        ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝      ╚═════╝ ╚══════╝
```

# 🎓 Smart Campus Event Management System

### *The next-generation platform for discovering, managing, and experiencing campus events*

<br/>

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![H2](https://img.shields.io/badge/H2-Database-004088?style=for-the-badge&logo=h2&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.x-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-Basic%20Auth-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Gemini AI](https://img.shields.io/badge/Google%20Gemini-AI%20Powered-4285F4?style=for-the-badge&logo=google&logoColor=white)

<br/>

![Build](https://img.shields.io/badge/Build-Passing-00ff88?style=flat-square)
![Errors](https://img.shields.io/badge/Compilation%20Errors-Zero-00ff88?style=flat-square)
![Lombok](https://img.shields.io/badge/Lombok-Removed-ff2d78?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)

<br/>

| 📄 Pages | ☕ Java Files | 🔗 REST Endpoints | 🎪 Sample Events |
|:---:|:---:|:---:|:---:|
| **3** | **19** | **14** | **8** |

</div>

---

## 📋 Table of Contents

- [Problem Statement](#-problem-statement)
- [Our Solution](#-our-solution)
- [Features](#-features)
- [Technologies Used](#-technologies-used)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Page Structure & UI](#-page-structure--ui)
- [Database Schema](#-database-schema)
- [REST API Reference](#-rest-api-reference)
- [AI Recommendation System](#-ai-recommendation-system)
- [Setup & Installation](#-setup--installation)
- [How to Run](#-how-to-run)
- [Access Credentials](#-access-credentials)
- [Important Notes](#-important-notes)
- [Screenshots Overview](#-screenshots-overview)
- [Common Issues & Fixes](#-common-issues--fixes)

---

## ❗ Problem Statement

Campus event management in most colleges is chaotic and fragmented. Events are announced on notice boards, registrations happen via WhatsApp groups, and there is **zero centralized tracking** of attendance or feedback.

| Pain Point | Problem |
|---|---|
| 📭 **No Central Platform** | Events are scattered across emails, posters, and social media. Students miss events they would have attended. |
| 📝 **Manual Registrations** | Paper-based or spreadsheet-driven registration has no capacity enforcement, duplicate detection, or real-time availability. |
| 📊 **Zero Admin Visibility** | Admins cannot filter events by department, date, or type. No dashboards showing registration counts or statistics. |
| 🔇 **No Feedback Loop** | After events, there is no structured way to collect student ratings, making it impossible to improve future events. |
| 🤖 **No Personalization** | Students must manually browse all events with no intelligent filtering based on their interests. |

---

## ✅ Our Solution

**Smart Campus Event Management System** is a full-stack web application built with Spring Boot 3 that provides a unified platform for the entire campus event lifecycle — from discovery to feedback.

```
Student Journey:   Landing → Login → Browse Events → AI Recommendations → Register → View My Events → Submit Feedback
Admin Journey:     Landing → Login → Admin Panel → Add/Edit/Delete Events → View Stats & Filters
```

| Solution | Description |
|---|---|
| 🖥️ **Unified Event Hub** | Single dashboard with all upcoming events — type badges, capacity bars, venue, and real-time seat availability |
| 🔒 **Smart Registration Engine** | Server-side rules prevent duplicates, enforce capacity limits, and reject registrations for invalid event states |
| ⚙️ **Admin Control Panel** | Secured with HTTP Basic Auth — full CRUD, multi-field filtering, date range search, and live statistics |
| ⭐ **Structured Feedback** | 5-star ratings + written comments per event; admins can retrieve average ratings via REST API |
| 🤖 **AI Recommendations** | Google Gemini AI scores every event against student interests and returns a ranked list with match reasons |

---

## ✨ Features

### 🎓 Student Features
- ✅ Browse all upcoming campus events in a responsive neon grid
- ✅ Filter events by department, event type, and keyword (client-side, instant)
- ✅ Register for events with real-time capacity checking
- ✅ Duplicate registration prevention (same email + same event = rejected)
- ✅ View all personal registrations by email lookup
- ✅ Submit star ratings (1–5) and written feedback for any event
- ✅ Toast notifications for all actions (success/error)
- ✅ **AI-powered event recommendations based on personal interests**

### ⚙️ Admin Features
- ✅ View live dashboard statistics (total events, upcoming, completed, total registrations, active departments)
- ✅ Create new events with full field validation
- ✅ Edit existing events via pre-populated modal form
- ✅ Delete events with confirmation dialog
- ✅ Search/filter events by: department, type, status, from-date, to-date
- ✅ View all events including completed and cancelled ones
- ✅ Secured with Spring Security HTTP Basic Authentication

### 🤖 AI Recommendation Features
- ✅ Enter any interest string (e.g. "AI, coding, robotics") to get personalized event suggestions
- ✅ Each recommendation includes a **match score (0–100)** and a **one-line AI reason**
- ✅ Results are ranked by relevance and labelled HIGH / MEDIUM / LOW match
- ✅ Powered by **Google Gemini 2.5 Flash** via a single optimized API call
- ✅ Graceful fallback — if the AI call fails, a friendly message is shown without crashing

### 🎨 UI/UX Features
- ✅ Futuristic neon pink + dark theme
- ✅ 3D liquid-metal animated CSS blob on landing page
- ✅ Floating particle system animation
- ✅ Animated neon grid background
- ✅ Smooth hover transitions and card animations
- ✅ Fully responsive design
- ✅ Google Fonts: Orbitron, Rajdhani, Share Tech Mono

---

## 🛠️ Technologies Used

### Backend
| Technology | Version | Purpose |
|---|---|---|
| **Spring Boot** | 3.2.4 | Application framework, auto-configuration, embedded Tomcat |
| **Spring MVC** | Included | `@Controller` for HTML pages, `@RestController` for REST APIs |
| **Spring Data JPA** | Included | `JpaRepository`, `@Entity` mapping, JPQL custom `@Query` methods |
| **Spring Security** | Included | HTTP Basic Authentication for `/admin/**` routes |
| **Jakarta Validation** | Included | `@NotBlank`, `@Size`, `@Email`, `@Pattern`, `@Min`, `@Max` |
| **H2 Database** | Runtime | In-memory embedded database, zero installation required |
| **Google Gemini AI** | v1beta | AI-powered event recommendations via `generateContent` REST API |
| **RestTemplate** | Included | HTTP client used to call the Gemini API |
| **Jackson** | Included | JSON serialization/deserialization of Gemini request and response |

### Frontend
| Technology | Purpose |
|---|---|
| **Thymeleaf** | Server-side HTML templating with `th:each`, `th:if`, `th:field`, `th:errors`, `#temporals` |
| **HTML5 + CSS3** | Pure CSS animations: 3D blob, neon glow, particle system, hover transitions |
| **Google Fonts** | Orbitron (display), Rajdhani (body), Share Tech Mono (monospaced labels) |
| **Vanilla JavaScript** | Fetch API for REST calls, modal management, client-side filtering, AI recommendation UI |

### Build & Tools
| Tool | Version | Purpose |
|---|---|---|
| **Apache Maven** | 3.8+ | Dependency management, build lifecycle, JAR packaging |
| **Java** | 17 (LTS) | Programming language |
| **Spring Boot Maven Plugin** | Included | Builds executable fat JAR |

---

## 🏛️ Architecture

The application follows a strict **4-layer architecture** where each layer only communicates with the layer directly below it:

```
┌─────────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                           │
│   landing.html │ login.html │ dashboard.html │ admin.html       │
│              Thymeleaf Templates + CSS3 Animations              │
└────────────────────────────┬────────────────────────────────────┘
                             │  HTTP Requests / Model & View
┌────────────────────────────▼────────────────────────────────────┐
│                    CONTROLLER LAYER                             │
│  PageController(@Controller) │ EventController(@RestController) │
│  RegistrationController(@RestController)                        │
│  RecommendationController(@RestController) │ GlobalExceptionHandler│
└────────────────────────────┬────────────────────────────────────┘
                             │  Method calls
┌────────────────────────────▼────────────────────────────────────┐
│                     SERVICE LAYER                               │
│     EventService │ RegistrationService │ FeedbackService        │
│     RecommendationService (→ Gemini API) │ CalendarService      │
│         Business Logic + Validation Rules + @Transactional      │
└────────────────────────────┬────────────────────────────────────┘
                             │  Repository calls
┌────────────────────────────▼────────────────────────────────────┐
│                   REPOSITORY LAYER                              │
│   EventRepository │ RegistrationRepository │ FeedbackRepository │
│              JpaRepository + Custom @Query (JPQL)               │
└────────────────────────────┬────────────────────────────────────┘
                             │  SQL via Hibernate
┌────────────────────────────▼────────────────────────────────────┐
│                      DATABASE LAYER                             │
│           H2 In-Memory │ EVENTS │ REGISTRATIONS │ FEEDBACKS     │
│                    DDL auto: create-drop                         │
└─────────────────────────────────────────────────────────────────┘
                             │
                 ┌───────────▼────────────┐
                 │   EXTERNAL AI LAYER    │
                 │  Google Gemini API     │
                 │  v1beta · gemini-2.5-flash│
                 │  generateContent REST  │
                 └────────────────────────┘
```

### Spring MVC Request Flow

```
Browser Request
      │
      ▼
DispatcherServlet (Spring MVC Front Controller)
      │
      ├─── Security Filter ──── /admin/** ──── Basic Auth Check
      │
      ├─── PageController ──── GET /  ────────► landing.html
      │                   ──── GET /login ─────► login.html
      │                   ──── POST /login ────► redirect:/dashboard
      │                   ──── GET /dashboard ─► dashboard.html
      │                   ──── GET /admin/** ──► admin.html
      │
      ├─── EventController ─── GET /api/events ────────► JSON List
      │                    ─── POST /api/admin/events ──► JSON Event
      │
      ├─── RecommendationController ─ GET /api/recommendations ► JSON (via Gemini)
      │
      └─── RegistrationController ─ POST /api/events/{id}/register ► JSON
```

---

## 📁 Project Structure

```
SmartCampusFix/
├── pom.xml                                          ← Maven config (NO Lombok)
│
└── src/
    ├── main/
    │   ├── java/com/campus/events/
    │   │   │
    │   │   ├── SmartCampusApplication.java           ← @SpringBootApplication entry point
    │   │   │
    │   │   ├── config/
    │   │   │   └── SecurityConfig.java               ← Basic Auth, route protection rules
    │   │   │
    │   │   ├── controller/
    │   │   │   ├── PageController.java               ← Serves HTML pages (@Controller)
    │   │   │   ├── EventController.java              ← Event CRUD REST API (@RestController)
    │   │   │   ├── RegistrationController.java       ← Registration + Feedback REST API
    │   │   │   └── RecommendationController.java     ← AI recommendations + calendar endpoints
    │   │   │
    │   │   ├── dto/
    │   │   │   ├── StudentDTO.java                   ← Login form data (name, email, phone)
    │   │   │   ├── FeedbackDTO.java                  ← Feedback submission data
    │   │   │   └── RecommendationResult.java         ← DTO: event + matchLevel + score + reason
    │   │   │
    │   │   ├── exception/
    │   │   │   ├── GlobalExceptionHandler.java       ← @ControllerAdvice — all error handling
    │   │   │   ├── EventNotFoundException.java       ← Thrown when event ID not found
    │   │   │   └── RegistrationException.java        ← Business rule violations (full, duplicate)
    │   │   │
    │   │   ├── model/
    │   │   │   ├── Event.java                        ← @Entity — EventType + EventStatus enums
    │   │   │   ├── Registration.java                 ← @Entity — @ManyToOne → Event
    │   │   │   └── Feedback.java                     ← @Entity — rating 1 to 5, comments
    │   │   │
    │   │   ├── repository/
    │   │   │   ├── EventRepository.java              ← JpaRepository + 8 custom JPQL queries
    │   │   │   ├── RegistrationRepository.java       ← Duplicate check, by-email lookup
    │   │   │   └── FeedbackRepository.java           ← Average rating query
    │   │   │
    │   │   └── service/
    │   │       ├── EventService.java                 ← CRUD, statistics, search logic
    │   │       ├── RegistrationService.java          ← Registration rules, cancel logic
    │   │       ├── FeedbackService.java              ← Submit + retrieve feedback
    │   │       ├── RecommendationService.java        ← Gemini API call + score parsing
    │   │       └── CalendarService.java              ← ICS generation + conflict detection
    │   │
    │   └── resources/
    │       ├── application.properties                ← DB, security, Thymeleaf, Gemini config
    │       ├── data.sql                              ← 8 sample events (auto-loaded on start)
    │       └── templates/
    │           ├── landing.html                      ← Page 1: 3D blob, particles, CTA button
    │           ├── login.html                        ← Page 2: form + Spring Validation errors
    │           ├── dashboard.html                    ← Page 3 (Student): grid, register, feedback, AI
    │           ├── admin.html                        ← Page 3 (Admin): CRUD, stats, filter table
    │           └── error.html                        ← Custom error page (404, 500 etc.)
    │
    └── test/
        └── java/com/campus/events/
            └── SmartCampusApplicationTests.java      ← Context load test
```

---

## 🗄️ Database Schema

### Events Table
```sql
CREATE TABLE events (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    title            VARCHAR(100)    NOT NULL,
    description      TEXT            NOT NULL,
    event_date       DATE            NOT NULL,
    event_time       TIME,
    venue            VARCHAR(200),
    department       VARCHAR(255),
    event_type       VARCHAR(50),    -- CONFERENCE | HACKATHON | WORKSHOP | CULTURAL
                                     -- CAREER | EXHIBITION | SEMINAR | COMPETITION
    capacity         INTEGER,
    registered_count INTEGER         DEFAULT 0,
    status           VARCHAR(50)     DEFAULT 'UPCOMING'
                                     -- UPCOMING | ONGOING | COMPLETED | CANCELLED
);
```

### Registrations Table
```sql
CREATE TABLE registrations (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_name   VARCHAR(100)   NOT NULL,
    student_email  VARCHAR(255)   NOT NULL,
    phone_number   VARCHAR(20),
    event_id       BIGINT         NOT NULL REFERENCES events(id),
    registered_at  TIMESTAMP,
    status         VARCHAR(50)    DEFAULT 'CONFIRMED',
                                  -- CONFIRMED | CANCELLED | WAITLISTED
    CONSTRAINT uk_student_event UNIQUE (student_email, event_id)
);
```

### Feedbacks Table
```sql
CREATE TABLE feedbacks (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_name  VARCHAR(255),
    student_email VARCHAR(255),
    rating        INTEGER        NOT NULL,   -- 1 to 5
    comments      TEXT,
    event_id      BIGINT         NOT NULL REFERENCES events(id),
    submitted_at  TIMESTAMP
);
```

### Entity Relationships
```
Event (1) ──────────────────── (*) Registration
  │                                  student_name
  │                                  student_email
  │                                  phone_number
  │                                  status: CONFIRMED | CANCELLED
  │
  └──────────────────────────── (*) Feedback
                                     rating (1-5)
                                     comments
                                     submitted_at
```

---

## 🔗 REST API Reference

### Base URL: `http://localhost:8080`

---

### 📖 Public Endpoints (No Authentication Required)

#### Events

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/events` | Get all upcoming events |
| `GET` | `/api/events/{id}` | Get a single event by ID |
| `GET` | `/api/events/search` | Search events with optional filters |

**Search Query Parameters:**
```
GET /api/events/search?department=Computer Science&eventType=WORKSHOP&status=UPCOMING&fromDate=2025-09-01&toDate=2025-12-31
```
| Parameter | Type | Required | Example |
|---|---|---|---|
| `department` | String | No | `Computer Science` |
| `eventType` | Enum | No | `CONFERENCE \| HACKATHON \| WORKSHOP \| CULTURAL \| CAREER \| EXHIBITION \| SEMINAR \| COMPETITION` |
| `status` | Enum | No | `UPCOMING \| ONGOING \| COMPLETED \| CANCELLED` |
| `fromDate` | Date (ISO) | No | `2025-09-01` |
| `toDate` | Date (ISO) | No | `2025-12-31` |

#### Registrations

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/events/{eventId}/register` | Register a student for an event |
| `GET` | `/api/registrations?email={email}` | Get all registrations for a student |
| `DELETE` | `/api/registrations/{id}?email={email}` | Cancel a registration |

**Registration Request Body:**
```json
POST /api/events/1/register
Content-Type: application/json

{
    "name": "Arjun Sharma",
    "email": "arjun@college.edu",
    "phone": "9876543210"
}
```

**Registration Response:**
```json
{
    "message": "Registration successful!",
    "registrationId": 1,
    "eventTitle": "Tech Summit 2025",
    "studentName": "Arjun Sharma"
}
```

#### Feedback

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/feedback` | Submit feedback for an event |
| `GET` | `/api/events/{eventId}/feedback` | Get all feedback and average rating for an event |

**Feedback Request Body:**
```json
POST /api/feedback
Content-Type: application/json

{
    "studentName": "Priya Singh",
    "studentEmail": "priya@college.edu",
    "eventId": 1,
    "rating": 5,
    "comments": "Absolutely brilliant! Best event of the semester."
}
```

#### AI Recommendations

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/recommendations?interest={interest}` | Get AI-ranked events for a student's interests |

**Example Request:**
```
GET /api/recommendations?interest=AI%2C+machine+learning%2C+robotics
```

**Example Response:**
```json
[
  {
    "eventId": 1,
    "title": "Tech Summit 2025",
    "eventType": "CONFERENCE",
    "eventDate": "2026-08-15",
    "venue": "Auditorium A",
    "department": "Computer Science",
    "capacity": 200,
    "registeredCount": 45,
    "matchLevel": "HIGH",
    "matchScore": 95,
    "matchReason": "The conference directly features AI workshops and discussions."
  },
  {
    "eventId": 4,
    "title": "Data Science Workshop",
    "eventType": "WORKSHOP",
    "eventDate": "2026-09-10",
    "venue": "Lab 3",
    "department": "Computer Science",
    "capacity": 60,
    "registeredCount": 58,
    "matchLevel": "HIGH",
    "matchScore": 90,
    "matchReason": "The workshop covers Machine Learning fundamentals directly aligned with interests."
  }
]
```

| Field | Type | Description |
|---|---|---|
| `matchLevel` | String | `HIGH` (score ≥ 65) · `MEDIUM` (score ≥ 35) · `LOW` (score < 35) |
| `matchScore` | Integer | 0–100 relevance score assigned by Gemini AI |
| `matchReason` | String | One-sentence explanation from the AI |

#### Calendar

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/calendar/export/{eventId}?name=&email=` | Download a `.ics` calendar file for an event |
| `GET` | `/api/calendar/conflict/{eventId}?email=` | Check if registering would cause a time conflict |

---

### 🔐 Admin Endpoints (HTTP Basic Auth Required)

> **Credentials:** `admin` / `admin123`

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/admin/events` | Get ALL events (including completed/cancelled) |
| `POST` | `/api/admin/events` | Create a new event |
| `PUT` | `/api/admin/events/{id}` | Update an existing event |
| `DELETE` | `/api/admin/events/{id}` | Delete an event |
| `GET` | `/api/admin/stats` | Get dashboard statistics |
| `GET` | `/api/admin/events/{eventId}/registrations` | Get all registrations for an event |

**Create/Update Event Request Body:**
```json
POST /api/admin/events
Authorization: Basic YWRtaW46YWRtaW4xMjM=
Content-Type: application/json

{
    "title": "AI & Machine Learning Summit",
    "description": "A full-day conference on the latest trends in AI and ML with hands-on workshops.",
    "eventDate": "2025-11-10",
    "eventTime": "09:00:00",
    "venue": "Main Auditorium",
    "department": "Computer Science",
    "eventType": "CONFERENCE",
    "capacity": 300,
    "status": "UPCOMING"
}
```

**Statistics Response:**
```json
GET /api/admin/stats

{
    "totalEvents": 8,
    "upcomingEvents": 8,
    "completedEvents": 0,
    "cancelledEvents": 0,
    "totalRegistrations": 658,
    "departments": [
        "Arts Club",
        "Computer Science",
        "Cultural Committee",
        "MBA Department",
        "Mechanical Engineering",
        "Placement Cell"
    ]
}
```

---

### 🌐 Page Routes

| Route | Controller | Description |
|---|---|---|
| `GET /` | PageController | Landing page |
| `GET /login` | PageController | Login form |
| `POST /login` | PageController | Process login, redirect to dashboard |
| `GET /dashboard` | PageController | Student dashboard |
| `GET /admin/dashboard` | PageController | Admin panel (Basic Auth required) |
| `GET /h2-console` | H2 | H2 database browser console (dev only) |

---

## 🤖 AI Recommendation System

### How It Works

The recommendation engine uses **Google Gemini 2.5 Flash** to intelligently score every event in the database against a student's free-text interest string.

```
Student types interest  →  RecommendationController
        │
        ▼
RecommendationService.getRecommendedEvents(interest)
        │
        ├── Fetch all events from EventRepository
        │
        ├── Build a single prompt with all event titles, types & descriptions
        │
        ▼
Google Gemini API (v1beta · gemini-2.5-flash)
  POST https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent
        │
        ▼
  Returns JSON array:  [{ id, score, reason }, ...]
        │
        ├── Parse scores → map to Event entities
        ├── Assign MatchLevel: HIGH / MEDIUM / LOW
        ├── Sort by score DESC
        └── Return ranked RecommendationResult list
                │
                ▼
        RecommendationController
        → JSON response to dashboard.html
        → Rendered as neon recommendation cards
```

### Single API Call Design

All events are sent in **one prompt** to Gemini, making it cost-efficient regardless of how many events exist. Gemini scores all events in one shot and returns a ranked JSON array.

### Configuration

Set the following in `application.properties`:

```properties
# ── Google Gemini Configuration ───────────────────────
# Get your free key from: https://aistudio.google.com/app/apikey
gemini.api.key=YOUR_API_KEY_HERE
gemini.api.model=gemini-2.5-flash
```

> ⚠️ **Important:** Use model `gemini-2.5-flash` with API version `v1beta`. Other combinations (`gemini-2.0-flash` on `v1`, `gemini-1.5-flash-8b` on `v1`) result in 404 errors. The free tier quota for `gemini-2.5-flash` on `v1beta` is generous — up to 1 million tokens/day.

### Match Level Thresholds

| Score Range | Match Level | Meaning |
|---|---|---|
| 65 – 100 | 🟢 HIGH | Strongly aligned with student interests |
| 35 – 64 | 🟡 MEDIUM | Partially relevant |
| 0 – 34 | 🔴 LOW | Little to no relevance |

Events with a score of 0 are excluded from results entirely.

### Fallback Behavior

If the Gemini API call fails for any reason (network error, quota exceeded, invalid key), the service returns an empty list gracefully and the UI shows "Recommendations not available. Please try again later." — the rest of the application continues working normally.

---

## ⚙️ Setup & Installation

### Prerequisites

Ensure the following are installed before proceeding:

| Tool | Minimum Version | Check Command |
|---|---|---|
| Java JDK | 17 | `java -version` |
| Apache Maven | 3.8 | `mvn -version` |

### Step 1 — Get the Project

**Option A — Download ZIP (Recommended for beginners):**
```
Download SmartCampusFixed.zip → Extract to your preferred folder
```

**Option B — Clone from Git:**
```bash
git clone https://github.com/yourusername/smart-campus-events.git
cd smart-campus-events
```

### Step 2 — Add Your Gemini API Key

1. Go to [https://aistudio.google.com/app/apikey](https://aistudio.google.com/app/apikey)
2. Click **"Create API Key"** and copy the key
3. Open `src/main/resources/application.properties`
4. Replace `YOUR_API_KEY_HERE` on the `gemini.api.key` line with your actual key

### Step 3 — Verify Project Structure

Make sure your extracted folder contains:
```
SmartCampusFix/
    ├── pom.xml           ← Must exist at root
    └── src/              ← Source code folder
```

> ✅ **Folder Location:** Any path works. Avoid folder names with spaces for best compatibility on Windows.
> Examples that work fine:
> - `C:\Users\admin\Documents\Projects\SmartCampusFix`
> - `C:\Users\admin\Downloads\SmartCampusFix`

---

## 🚀 How to Run

### Method 1 — Maven (Recommended)

```bash
# Step 1: Navigate to project root (where pom.xml lives)
cd C:\Users\admin\Documents\Projects\SmartCampusFix

# Step 2: Clean previous builds and compile
mvn clean install

# Step 3: Run the application
mvn spring-boot:run
```

### Method 2 — Executable JAR

```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/smart-campus-events-1.0.0.jar
```

### Method 3 — IntelliJ IDEA

1. Open IntelliJ IDEA → **File → Open** → select project folder
2. Wait for Maven to download dependencies (watch the bottom progress bar)
3. Open `SmartCampusApplication.java`
4. Click the green **▶** button next to `main()` method

---

### ✅ Successful Startup Output

```
╔══════════════════════════════════════════════╗
║  Smart Campus Event Management System        ║
║  Running at: http://localhost:8080           ║
║  H2 Console: http://localhost:8080/h2-console║
║  Admin:      admin / admin123                ║
╚══════════════════════════════════════════════╝
```

---

### 🌐 Access URLs

| URL | Description |
|---|---|
| `http://localhost:8080` | Landing page |
| `http://localhost:8080/login` | Student login |
| `http://localhost:8080/dashboard` | Student dashboard |
| `http://localhost:8080/admin/dashboard` | Admin panel (prompts for login) |
| `http://localhost:8080/h2-console` | H2 database browser |
| `http://localhost:8080/api/recommendations?interest=AI` | AI recommendations (direct API test) |

**H2 Console Settings:**
```
JDBC URL:  jdbc:h2:mem:campusdb
Username:  sa
Password:  (leave empty)
```

---

## 🔑 Access Credentials

### Admin (HTTP Basic Authentication)
```
Username : admin
Password : admin123
Auth Type: HTTP Basic (browser popup)
Routes   : /admin/** and /api/admin/**
```

### Student (No Authentication Required)
```
Name  : Any valid name (min 2 characters)
Email : Any valid email address
Phone : Any valid 10-digit Indian mobile number (starts with 6-9)
Auth  : None — students access the dashboard freely
```

---

## ⚠️ Important Notes

### 🔧 Why Lombok Was Removed

The original version of this project used Lombok annotations:
```java
// ORIGINAL — breaks without Annotation Processing enabled
@Data
@Slf4j
@Builder
@RequiredArgsConstructor
public class Event { ... }
```

Without enabling **Annotation Processing** in IntelliJ IDEA, Lombok silently generates nothing. This caused **49 compilation errors** like:
```
cannot find symbol: method getTitle()
cannot find symbol: variable log
cannot find symbol: method builder()
variable eventService not initialized in the default constructor
```

**This fixed version removes Lombok entirely** and uses explicit Java:

```java
// FIXED — works everywhere, zero tool configuration needed
public class Event {
    private String title;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}

// Explicit logger
private static final Logger log = Logger.getLogger(EventService.class.getName());

// Explicit constructor injection
public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
}
```

---

### 🤖 Gemini API — Key Notes

- **Model:** Always use `gemini-2.5-flash` — it is the only model confirmed working on the free tier with `v1beta`
- **API Version:** Always use `v1beta` in the URL — `v1` returns 404 for this model
- **Free Quota:** ~1 million tokens/day, 15 requests/minute — sufficient for normal use
- **Key Security:** Never commit your API key to Git. Add `application.properties` to `.gitignore` or use environment variables in production
- **Rate Limits:** If you hit 429 errors, wait 60 seconds and try again, or generate a new key from AI Studio

---

### 💾 H2 is In-Memory — Data Resets on Restart

H2 stores data **only while the application is running**. Everything is lost when the server stops.

- ✅ The 8 sample events are **re-inserted from `data.sql` automatically** on every startup
- ❌ Student registrations and feedback are lost on restart

**To use persistent storage**, switch to MySQL in `application.properties`:
```properties
# Replace H2 config with:
spring.datasource.url=jdbc:mysql://localhost:3306/campusdb
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```
And add MySQL dependency to `pom.xml`:
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

## 📸 Screenshots Overview

> *The application features a futuristic neon pink + dark theme across all pages.*

### Page 1 — Landing Page
```
┌─────────────────────────────────────────────────────────────┐
│  ⟨ dark background with animated neon grid ⟩                │
│                                                             │
│              // Campus Intelligence System v2.0             │
│                                                             │
│           ┌──────────────────────────────────┐             │
│           │  [  3D LIQUID-METAL BLOB  ]       │            │
│           │  ████   SMART              ████   │            │
│           │  ████   CAMPUS  ←gradient  ████   │            │
│           └──────────────────────────────────┘             │
│                                                             │
│     "The next-generation platform for campus events"        │
│                                                             │
│            ╔══════════════════════════════╗                 │
│            ║  LET'S GET STARTED  →        ║                 │
│            ╚══════════════════════════════╝                 │
│                                                             │
│      8+ Events    |   500+ Registrations   |  6+ Depts      │
└─────────────────────────────────────────────────────────────┘
```

### Page 2 — Login
```
┌─────────────────────────────────────────────────────────────┐
│           ╔══════════════════════════════════╗               │
│           ║  // Identify Yourself            ║               │
│           ║         SIGN IN                  ║               │
│           ║                                  ║               │
│           ║  ● Full Name                     ║               │
│           ║  ┌────────────────────────────┐  ║               │
│           ║  │ Arjun Sharma               │  ║               │
│           ║  └────────────────────────────┘  ║               │
│           ║  ● Email Address                 ║               │
│           ║  ┌────────────────────────────┐  ║               │
│           ║  │ arjun@college.edu          │  ║               │
│           ║  └────────────────────────────┘  ║               │
│           ║  ● Phone Number                  ║               │
│           ║  ┌────────────────────────────┐  ║               │
│           ║  │ 9876543210                 │  ║               │
│           ║  └────────────────────────────┘  ║               │
│           ║  ╔══ ACCESS DASHBOARD → ═══════╗ ║               │
│           ╚══════════════════════════════════╝               │
└─────────────────────────────────────────────────────────────┘
```

### Page 3 — Student Dashboard
```
┌─────────────────────────────────────────────────────────────┐
│  SMART CAMPUS │ Events │ My Events │ Feedback   Welcome ●   │
├─────────────────────────────────────────────────────────────┤
│  Upcoming Events                           [8 EVENTS]        │
│  [All Depts ▼] [All Types ▼] [Search...]   SEARCH  RESET    │
├──────────────┬──────────────┬──────────────┬────────────────┤
│ CONFERENCE   │ HACKATHON    │ CULTURAL     │ WORKSHOP       │
│ Tech Summit  │ Code for     │ Cultural     │ Data Science   │
│ 2025         │ Future       │ Fiesta       │ Workshop       │
│ ▓▓▓░░ 45/200 │ ▓▓▓░░ 32/100 │ ▓▓▓░ 210/500 │ ▓▓▓▓▓ 58/60  │
│ [Register→]  │ [Register→]  │ [Register→]  │ [Event Full]   │
└──────────────┴──────────────┴──────────────┴────────────────┘

┌─────────────────────────────────────────────────────────────┐
│  🤖 AI RECOMMENDATIONS                                       │
│  ┌──────────────────────────────────────────────────────┐   │
│  │ Your interests: [ AI, machine learning          ]    │   │
│  │                          [ GET RECOMMENDATIONS → ]   │   │
│  └──────────────────────────────────────────────────────┘   │
│                                                             │
│  ┌─────────────────────┐  ┌─────────────────────┐          │
│  │ 🟢 HIGH  Score: 95  │  │ 🟢 HIGH  Score: 90  │          │
│  │ Tech Summit 2025    │  │ Data Science Workshop│          │
│  │ CONFERENCE          │  │ WORKSHOP             │          │
│  │ "Directly covers AI │  │ "Covers ML topics    │          │
│  │  workshops."        │  │  aligned with you."  │          │
│  │ [Register →]        │  │ [Register →]         │          │
│  └─────────────────────┘  └─────────────────────┘          │
└─────────────────────────────────────────────────────────────┘
```

---

## 🐛 Common Issues & Fixes

### ❌ Port 8080 Already in Use
```
Error: Web server failed to start. Port 8080 was already in use.
```
**Fix:** Change the port in `src/main/resources/application.properties`:
```properties
server.port=8090
```
Then access the app at `http://localhost:8090`.

---

### ❌ `mvn` is not recognized
```
'mvn' is not recognized as an internal or external command
```
**Fix:** Maven is not added to PATH. Either:
- Add Maven's `bin` folder to your system PATH, or
- Use the full path: `C:\apache-maven-3.x.x\bin\mvn spring-boot:run`

---

### ❌ `java -version` shows version below 17
```
java version "11.0.x"
```
**Fix:** Install Java 17+ from [adoptium.net](https://adoptium.net) and set `JAVA_HOME` to the JDK 17 installation directory.

---

### ❌ H2 Console shows "jdbc:h2:~/test" (wrong URL)
**Fix:** Change the JDBC URL in the H2 console login page to:
```
jdbc:h2:mem:campusdb
```

---

### ❌ Admin panel says "Unauthorized" or loops on Basic Auth
**Fix:** Clear your browser's cached credentials for `localhost:8080`, then re-enter:
- Username: `admin`
- Password: `admin123`

---

### ❌ Sample data not loading (events table empty)
**Fix:** Check `application.properties` has:
```properties
spring.sql.init.mode=always
spring.jpa.hibernate.ddl-auto=create-drop
```
Both must be set correctly for `data.sql` to run on startup.

---

### ❌ AI Recommendations show "Recommendations not available"
```
Gemini call failed — returning empty recommendations.
```
Check the Spring Boot console for the exact reason. Common causes and fixes:

| Error | Fix |
|---|---|
| `404 NOT_FOUND` — wrong model name | Use `gemini.api.model=gemini-2.5-flash` in `application.properties` |
| `429 RESOURCE_EXHAUSTED` — quota exceeded | Generate a new API key at [aistudio.google.com/app/apikey](https://aistudio.google.com/app/apikey) |
| `400 BAD_REQUEST` — malformed request | Ensure `v1beta` is in the URL (not `v1`) |
| Empty `gemini.api.key` | Paste your actual key in `application.properties` |
| JSON parse error / truncated response | Already fixed — `maxOutputTokens` is set to `8192` |

---

## 📦 Build Information

```
Group ID    : com.campus
Artifact ID : smart-campus-events
Version     : 1.0.0
Packaging   : jar
Java        : 17
Spring Boot : 3.2.4
```

---

## 📄 License

This project is licensed under the **MIT License** — free to use, modify, and distribute for educational and commercial purposes.

---

<div align="center">

**Built with ❤️ using Spring Boot · Thymeleaf · H2 · Spring Security · Google Gemini AI**

*Smart Campus Event Management System — Version 1.0.0*

```
// END OF DOCUMENTATION
```

</div>
