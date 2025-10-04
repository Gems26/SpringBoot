Student Grade System

Backend:
- Java 17, Spring Boot, H2 in-memory DB
- Run:
  cd backend
  mvn spring-boot:run
  H2 console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:studentsdb)

Frontend:
- React (create-react-app style)
- Run:
  cd frontend
  npm install
  npm start
  App will run at http://localhost:3000 and proxy API to backend.

This project is a minimal ready-to-run example with CRUD for students.
