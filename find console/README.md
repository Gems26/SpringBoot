# Console Finder API (Spring Boot 2.7.18)
Windows-ready project (VS Code)

## What you get
- Spring Boot 2.7.18 app with Kafka producer + consumer in the same application (Option A)
- Elasticsearch integration (designed for Elasticsearch 7.17.x)
- REST endpoints:
  - `POST /api/consoles` — publishes a Console object to Kafka topic `console-topic`
  - `GET  /api/consoles/search?q=...` — searches consoles in Elasticsearch index `console-index`

## Prerequisites (Windows)
- Java JDK 17+ (Adoptium Temurin recommended): https://adoptium.net/temurin/releases/?version=17
- Maven 3.8+: https://maven.apache.org/download.cgi
- Apache Kafka 3.x (unzipped somewhere, e.g. `C:\kafka`): https://kafka.apache.org/downloads
- Elasticsearch 7.17.x (unzipped somewhere, e.g. `C:\elasticsearch`): https://www.elastic.co/downloads/elasticsearch
- (Optional) Postman or curl for testing

## Quick start — Windows (CMD)

1. Start Zookeeper
```bat
cd C:\kafka
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

2. Start Kafka broker (new terminal)
```bat
cd C:\kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

3. Create topic (one-time)
```bat
cd C:\kafka
.\bin\windows\kafka-topics.bat --create --topic console-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

4. Start Elasticsearch (new terminal)
```bat
cd C:\elasticsearch
.\bin\elasticsearch.bat
```
Verify: open http://localhost:9200

5. Import project into VS Code (File → Open Folder) or use terminal:
```bat
cd C:\path\to\console-finder-api
mvn clean package
mvn spring-boot:run
```

6. Test API
Publish a console:
```bat
curl -X POST http://localhost:8080/api/consoles -H "Content-Type: application/json" -d "{"id":"1","name":"PlayStation 5","manufacturer":"Sony","generation":"9th","price":499.99}"
```
Search:
```bat
curl "http://localhost:8080/api/consoles/search?q=PlayStation"
```

7. Verify in Elasticsearch
```bat
curl "http://localhost:9200/console-index/_search?q=PlayStation&pretty"
```

## Notes & troubleshooting
- Ensure Kafka is reachable at `localhost:9092` and Elasticsearch at `http://localhost:9200`.
- Spring Boot 2.7.x pairs well with Elasticsearch 7.x — do not use ES 8.x without changing client config and security settings.
- If consumer doesn't save documents, check application logs and ensure `spring.kafka.consumer.properties.spring.json.trusted.packages=*` is set (it is by default in `application.properties`).
