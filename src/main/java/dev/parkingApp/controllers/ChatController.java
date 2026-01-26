package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.ChatRequest;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "*/api/base/chats", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public List<ChatRequest> getUserChats(@AuthenticationPrincipal AuthUser principal) {
        return chatService.getUserChats(principal.getUserId());
    }
}
