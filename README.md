# wacdo

Système de gestion des restaurants : suivi des restaurants, des collaborateurs et de
leurs affectations (poste occupé, période) au sein de la chaîne.

Projet de formation Spring Boot.

## Stack technique

| Élément | Version / techno |
| --- | --- |
| Langage | Java 21 |
| Framework | Spring Boot 4.1.1 (Web MVC, Data JPA, Thymeleaf, Security) |
| Base de données | MySQL 8.0 |
| ORM | Hibernate (JPA) |
| Utilitaires | Lombok, Spring Boot DevTools |
| Build | Maven (wrapper `mvnw` inclus) |


## Démarrage

### 1. Lancer l'infrastructure

```bash
docker compose -f src/main/resources/docker-compose.yaml up -d
```

Services démarrés :

| Service | Rôle | Accès |
| --- | --- | --- |
| `wacdo-mysql` | Base de données `wacdo_db` | `localhost:3306` |
| `wacdo-phpmyadmin` | Administration de la base | http://localhost:8081 |
| `smtp4dev-oauth2` | Serveur SMTP de test + webmail | http://localhost:8082 (SMTP : `localhost:2525`) |

### 2. Lancer l'application

```bash
./mvnw spring-boot:run
```

L'application est disponible sur http://localhost:8080.

### 3. Lancer les tests

```bash
./mvnw test
```