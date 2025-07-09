# 🛠️ Kafka + Spring Boot Integration using Docker

This project demonstrates how to set up Apache Kafka and Zookeeper using Docker and connect it with a Spring Boot application. It includes Kafka Producer and Consumer implementation with a REST API to test message publishing.

---

## 🛠️ Tech Stack

- **Spring Boot 3**
- **Apache Kafka**
- **Zookeeper**
- **Docker & Docker Compose**
- **Bitnami Kafka and Zookeeper Images**

---

## 📆 Features

- Kafka + Zookeeper setup using Docker
- Spring Boot Kafka Producer and Consumer
- REST API to send messages to Kafka
- Configured for local development via `host.docker.internal`
- Auto topic creation enabled

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/kafka-springboot-docker.git
cd kafka-springboot-docker
```

### 2. Start Kafka + Zookeeper with Docker

```bash
docker-compose up -d
```

> If needed, manually create a topic:

```bash
docker exec -it kafka bash
kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

### 3. Run Spring Boot App

```bash
./mvnw spring-boot:run
# or
./gradlew bootRun
```

---

## 🔧 Configuration

### ✅ `application.yml` (Kafka Configuration)

```yaml
spring:
  kafka:
    bootstrap-servers: host.docker.internal:9092
    consumer:
      group-id: kafkademo-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
```

### ✅ `docker-compose.yml`

```yaml
version: "3.8"

services:
  zookeeper:
    image: bitnami/zookeeper:3.8.0
    ports:
      - "2181:2181"
    environment:
      - ALLOW_ANONYMOUS_LOGIN=yes

  kafka:
    image: bitnami/kafka:3.6.1
    ports:
      - "9092:9092"
    environment:
      KAFKA_ENABLE_KRAFT: "no"
      KAFKA_CFG_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_CFG_LISTENERS: PLAINTEXT://:9092
      KAFKA_CFG_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
      KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT
      KAFKA_CFG_AUTO_CREATE_TOPICS_ENABLE: "true"
    depends_on:
      - zookeeper
```

---

## 🥺 API Example

```bash
POST http://localhost:8080/api/kafka/send
Body: { "message": "helloKafka" }

Response: Message sent to Kafka: helloKafka
```

---

## 🙌 Author

Built with ❤️ by [Raushan Kumar](https://github.com/your-username)\
If you find it helpful, give a ⭐ and share with devs!

---

## 📜 License

This project is open source and available under the [MIT License](LICENSE).

