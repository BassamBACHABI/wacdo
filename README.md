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

### Lancer l'application avec Docker

Après avoir démarré MySQL avec la commande de l'étape 1, construire l'image puis lancer l'application :

```bash
cp .env.example .env
# Modifier SPRING_DATASOURCE_PASSWORD dans .env avec le mot de passe de la base
docker build -t wacdo .
docker run --rm --name wacdo-app --env-file .env -e PORT=8080 -p 8080:8080 wacdo
```

L'application est alors disponible sur http://localhost:8080. Pour un déploiement sur un serveur, remplacer l'hôte `host.docker.internal` par le nom d'hôte ou l'adresse de la base de données accessible depuis le conteneur. Le fichier `.env` contient les identifiants et n'est pas suivi par Git.

### 3. Lancer les tests

```bash
./mvnw test
```

Les tests utilisent automatiquement le profil `test` et une base H2 en mémoire. Ils ne nécessitent pas de démarrer MySQL et n'utilisent pas les données de démonstration du profil `dev`.
