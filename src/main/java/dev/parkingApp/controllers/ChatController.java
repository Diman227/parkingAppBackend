package dev.parkingApp.controllers;

import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.dtos.response.ChatResponse;
import dev.parkingApp.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "*api/base/chats", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getUserChats(
            @AuthenticationPrincipal AuthUser principal) {
        return new ResponseEntity<>(chatService.getUserChats(principal.getUserId()), HttpStatus.OK);
    }
}
