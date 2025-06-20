# Customer-Demo: Vollständige Spring Boot + Docker + NGINX Demo

Dieses Projekt zeigt eine vollständige, moderne Fullstack-Lösung mit **Spring Boot (Backend, REST, Security, JPA, PostgreSQL)** und **NGINX (Frontend, statisch, Reverse Proxy)** – konfiguriert für sauberes CI/CD und lokale Entwicklung mit Docker Compose.

---

## Inhaltsverzeichnis

- [Features](#features)
- [Architektur-Überblick](#architektur-überblick)
- [Schnellstart](#schnellstart)
- [Projektstruktur](#projektstruktur)
- [Backend: Spring Boot API](#backend-spring-boot-api)
- [Frontend: Statisches HTML + JS](#frontend-statisches-html--js)
- [Sicherheit](#sicherheit)
- [Testing & Qualität](#testing--qualität)
- [CI/CD](#cicd)
- [Erweiterungen](#erweiterungen)

---

## Features

- **Benutzerverwaltung** (ADMIN/USER, Passwort-Hashing, Basic Auth)
- **Kundenverwaltung** (CRUD, REST-API)
- **Transaktionen für Kunden** (List, Create, REST-API)
- **Login/Logout, Rollenbasiertes UI** (Frontend)
- **PostgreSQL Datenbank** (Docker)
- **Spring Boot, JPA, Security**
- **Unit- & Integrationstests, CI/CD, Code-Qualität mit SpotBugs & JaCoCo**
- **Statisches HTML/JS-Frontend, NGINX-Proxy**
- **Komplettes Docker Compose Setup**

---

## Architektur-Überblick

```mermaid
graph TD
  NGINX[NGINX Frontend (Port 80)] -->|/api/* proxied| Backend(Spring Boot, Port 8080)
  NGINX -->|/ (statische HTML/JS)| User[Browser]
  Backend -->|JPA/Hibernate| DB[(PostgreSQL)]
```

- User nutzt statisches HTML/JS (über NGINX)
- API-Calls `/api/*` werden von NGINX an das Spring Boot Backend weitergeleitet
- Backend persistiert Daten in PostgreSQL

---

## Schnellstart

### Voraussetzungen

- Docker & Docker Compose
- (Optional: Maven & Java 17 für lokalen Backend-Build)

### Starten

```sh
docker-compose up --build
```

Öffne dann [http://localhost](http://localhost) im Browser.

**Standard-User für Login:**
- `admin` / `admin123` (ADMIN)
- `user` / `user123` (USER)

---

## Projektstruktur

```
.
├── backend/           # Spring Boot Backend (REST, Security, JPA)
│   ├── src/...
│   ├── Dockerfile
│   └── pom.xml
├── frontend/          # Statisches HTML/JS Frontend + NGINX Config
│   ├── html/
│   ├── nginx.conf
│   └── Dockerfile
├── docker-compose.yml # Multi-Container Orchestrierung
└── ...
```

---

## Backend: Spring Boot API

- **Technologien:** Spring Boot 3, Spring Security, Spring Data JPA, PostgreSQL
- **API-Endpunkte:**
  - `POST /api/customers` – Kunde anlegen (ROLE_USER, ROLE_ADMIN)
  - `GET /api/customers` – Alle Kunden (ROLE_USER, ROLE_ADMIN)
  - `POST /api/transactions` – Transaktion anlegen (ROLE_USER, ROLE_ADMIN)
  - `GET /api/transactions/customer/{id}` – Alle Transaktionen eines Kunden
  - `GET /api/userinfo` – Aktueller User & Rolle (für Frontend-Login)

**Sicherheit:**
- Basic Auth (HTTP Header)
- Passwörter sicher gehasht (BCrypt)
- Rollensteuerung in `SecurityConfig.java`
- Initiale User werden beim Start erzeugt (`DataInitializer`)

**Persistence:**
- JPA-Entities: `Customer`, `Transaction`, `User`
- Repositories: Standard Spring Data
- Datenbank: PostgreSQL (im Container, Service-Name `db`)

---

## Frontend: Statisches HTML + JS

- **Pfad:** `frontend/html/index.html`
- Kein Framework – reines HTML/CSS/JavaScript
- Features:
  - Kunden anzeigen & anlegen
  - Login/Logout (Basic Auth)
  - Transaktionen pro Kunde anzeigen & anlegen
  - Rollensensitives UI (ADMIN darf Kunden anlegen)
- Kommunikation mit Backend via REST-API (`/api/*`), Auth per Header

**NGINX:**  
- Liefert statische HTML/JS/CSS-Dateien aus
- Reverse Proxy für `/api/*` auf Backend-Service (`backend:8080`)

---

## Sicherheit

- **HTTP Basic Auth** (Frontend: Login-Form → Header)
- **Passwort-Hashing** mit BCrypt
- **Rollen:** `ADMIN`, `USER` (steuern API- und UI-Zugriff)
- **Spring Security** konfiguriert, inkl. Custom UserDetailsService

---

## Testing & Qualität

- **Unit-Tests:** z.B. Controller mit Mockito
- **Integration-Tests:** mit MockMvc, In-Memory-DB (H2)
- **Code Coverage:** JaCoCo
- **Static Analysis:** SpotBugs (Warnungen im JPA-Kontext dokumentiert)

---

## CI/CD

- **GitHub Actions Workflow:**  
  - Build & Test Backend (Maven)
  - Build Docker Images (Backend & Frontend)
  - Docker Compose Integration Test (Healthcheck auf Backend)
- **Docker Multi-Stage Builds** für kleine Images

---

## Erweiterungen & Tipps

- **Userverwaltung erweitern:** z.B. Registrierung, Passwort-Reset
- **Frontend mit Framework:** z.B. React oder Vue
- **JWT statt Basic Auth**
- **Automatisches DB-Seeding & Migrations (Flyway/Liquibase)**
- **Deployment in Cloud**

---

## Entwicklerhinweise

- Beim Ändern des Frontends: `docker-compose up --build` neu ausführen.
- Bei DB-Problemen: Container stoppen, Volumes löschen, neu starten.
- Für lokale Entwicklung kann das Backend auch einzeln per `mvn spring-boot:run` laufen.

---

## Demo-Logins

| Benutzername | Passwort  | Rolle  |
|--------------|-----------|--------|
| admin        | admin123  | ADMIN  |
| user         | user123   | USER   |

---

## Kontakt

Fragen, Feedback & Pull Requests willkommen!
