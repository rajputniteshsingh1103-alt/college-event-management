# 🎓 College Event Management System

A full-stack web application designed to streamline the process of organizing, managing, and participating in college events. Built with **Java Spring Boot**, this system provides a robust platform for administrators, teachers, and students.

## 🚀 Key Features
* **Admin Dashboard:** Full control over event creation, updates, and deletions.
* **User Authentication:** Secure login for Students and Teachers.
* **Event Registration:** Simple and intuitive flow for students to sign up for upcoming events.
* **Responsive UI:** Clean and modern interface built with HTML, CSS, and JavaScript.

## 🛠️ Tech Stack
* **Backend:** Java 17, Spring Boot 3.x, Spring Data JPA
* **Database:** MySQL (Relational Database Management)
* **Frontend:** HTML5, CSS3, JavaScript, Thymeleaf
* **Build Tool:** Maven

## 📁 Project Structure
* `controller/` - Handles incoming web requests.
* `model/` - Defines database entities (Event, User, Registration).
* `repository/` - Manages database interactions via JPA.
* `service/` - Contains core business logic.
* `resources/static/` - Contains CSS, JS, and Images.

## ⚙️ How to Run Locally
1.  Clone the repository:
    ```bash
    git clone https://github.com/rajputniteshsingh1103-alt/college-event-management.git
    ```
2.  Update `application.properties` with your MySQL credentials.
3.  Run the application through IntelliJ IDEA or use:
    ```bash
    mvn spring-boot:run
    ```
4.  Access the app at `http://localhost:8080`
