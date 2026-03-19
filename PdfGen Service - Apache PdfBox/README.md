
# Spring Boot PDF Generator

A minimal Spring Boot app that accepts text from the browser, generates a PDF using Apache PDFBox, shows the PDF inline, and provides a Download link.

## Prerequisites
- Java 17+
- Maven 3.9+

## Run
```bash
mvn clean spring-boot:run
# visit http://localhost:8080
```

## Build JAR
```bash
mvn clean package
java -jar target/spring-boot-pdf-0.0.1-SNAPSHOT.jar
```

## Endpoints
- `GET /` — input form
- `GET /result?text=...` — preview + download link
- `GET /pdf?text=...` — inline PDF
- `GET /pdf?download=true&text=...` — force download

## Notes
- Uses PDFBox 2.0.30. For non-Latin scripts, load a Unicode TTF via `PDType0Font.load(...)`.
