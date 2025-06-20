// Application.java
// Startpunkt der Spring Boot Anwendung
package com.cpb.customerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
   public static void main(String[] args) {
      // Startet eingebetteten Tomcat und initialisiert Spring-Kontext
      SpringApplication.run(Application.class, args);
   }
}
