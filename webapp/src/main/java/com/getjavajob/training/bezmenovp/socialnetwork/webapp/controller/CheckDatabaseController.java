package com.getjavajob.training.bezmenovp.socialnetwork.webapp.controller;

import com.getjavajob.training.bezmenovp.socialnetwork.service.DatabaseConnectionChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckDatabaseController {

    @Autowired
    private DatabaseConnectionChecker connectionChecker;

    @GetMapping("/check-database-connection")
    public ResponseEntity<String> checkDatabaseConnection() {
        boolean isConnected = connectionChecker.isDatabaseConnected();
        if (isConnected) {
            return ResponseEntity.ok("Database is connected!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to connect to the database.");
        }
    }

}