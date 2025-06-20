# Customer-Demo: Moderne Fullstack-Kundenverwaltung mit Transaktionen

## Was macht dieses Projekt? (Für Nutzer:innen)

Dieses Projekt ist eine **einfache, aber vollständige Kundenverwaltung** mit Web-Oberfläche – ideal als Demo für moderne Webentwicklung mit Java und Docker.  
Als Nutzer*in kannst du:

- Dich mit Benutzername und Passwort anmelden (z.B. als `admin` oder `user`)
- **Kunden anzeigen** und (mit ADMIN-Rechten) neue Kunden anlegen
- Für jeden Kunden **Transaktionen** anzeigen und neue Transaktionen erfassen
- Dich abmelden (Logout)
- Alles läuft bequem im Browser, kein Setup nötig – nur Docker starten!

**Zwei Rollen gibt es:**
- **ADMIN:** Darf Kunden und Transaktionen anlegen
- **USER:** Darf nur ansehen und Transaktionen anlegen

---

## Technologien und Architektur

**Backend:**  
- **Spring Boot 3**: REST-API, Security, Business-Logik
- **Spring Data JPA:** Objekt-Relationales Mapping (ORM) für PostgreSQL
- **Spring Security:** Rollenbasierte Authentifizierung (ADMIN/USER), Passwort-Hashing mit BCrypt
- **PostgreSQL**: Relationale Datenbank als Docker-Service

**Frontend:**  
- **Statisches HTML/CSS/JS:** Übersicht, Formulare und Logik im Browser, kein Framework notwendig
- **NGINX:** Liefert das Frontend aus und leitet API-Anfragen an das Backend weiter

**DevOps & Qualität:**  
- **Docker Compose:** Startet alle Dienste (Frontend, Backend, DB) mit einem Befehl
- **Unit- und Integrationstests:** Mit JUnit, Mockito, MockMvc
- **CI/CD:** GitHub Actions, automatischer Build, Tests und Integrationstest mit Docker Compose
- **SpotBugs, JaCoCo:** Code-Qualität und Testabdeckung

---

## Schnellstart

1. **Voraussetzungen:**  
   Docker & Docker Compose installiert

2. **Starten:**  
   Im Projektverzeichnis:
   ```sh
   docker-compose up --build
   ```
   Dann öffne [http://localhost](http://localhost) im Browser.

3. **Einloggen:**  
   - `admin` / `admin123` (ADMIN)
   - `user` / `user123` (USER)

---

## Weitere Infos

- **Alle Daten werden in der mitgelieferten PostgreSQL-DB gespeichert**
- **Standard-Benutzer werden beim ersten Start automatisch angelegt**
- **Das Projekt eignet sich als Vorlage für eigene Kunden-/Transaktionsverwaltung**

---

Für Details zu API, Code und Erweiterungsmöglichkeiten siehe weiter unten im README!