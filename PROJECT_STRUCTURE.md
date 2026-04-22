# Smart Campus Event Management System
## Project Structure

```
SmartCampusEventManagement/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── campus/
│       │           └── events/
│       │               ├── SmartCampusApplication.java        ← Entry point
│       │               ├── config/
│       │               │   └── SecurityConfig.java            ← Basic Auth for admin
│       │               ├── controller/
│       │               │   ├── PageController.java            ← Serves HTML pages
│       │               │   ├── EventController.java           ← REST: event CRUD
│       │               │   └── RegistrationController.java    ← REST: registrations
│       │               ├── dto/
│       │               │   ├── StudentDTO.java                ← Login form data
│       │               │   └── FeedbackDTO.java               ← Feedback form
│       │               ├── exception/
│       │               │   ├── GlobalExceptionHandler.java    ← @ControllerAdvice
│       │               │   ├── EventNotFoundException.java
│       │               │   └── RegistrationException.java
│       │               ├── model/
│       │               │   ├── Event.java                     ← @Entity
│       │               │   ├── Registration.java              ← @Entity
│       │               │   └── Feedback.java                  ← @Entity
│       │               ├── repository/
│       │               │   ├── EventRepository.java           ← JpaRepository
│       │               │   ├── RegistrationRepository.java
│       │               │   └── FeedbackRepository.java
│       │               └── service/
│       │                   ├── EventService.java
│       │                   ├── RegistrationService.java
│       │                   └── FeedbackService.java
│       └── resources/
│           ├── application.properties                         ← DB + Security config
│           ├── data.sql                                       ← Sample data
│           ├── schema.sql                                     ← DB schema
│           └── templates/
│               ├── landing.html                               ← Page 1
│               ├── login.html                                 ← Page 2
│               ├── dashboard.html                             ← Page 3 (Student)
│               ├── admin.html                                 ← Page 3 (Admin)
│               └── error.html                                 ← Error page
├── pom.xml                                                    ← Maven dependencies
└── README.md
```
