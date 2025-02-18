package com.therapp.spring.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/pruebaWS")
public class PruebaController {

    private PruebaBookService pruebaBookService;

    @PostMapping("/{userId}")
    public void postMethodName(@PathVariable String userId) {

        pruebaBookService.testWebSocket(userId);

    }
    


}