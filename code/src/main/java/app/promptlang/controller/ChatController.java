package app.promptlang.controller;

import app.promptlang.dto.ChatRequest;
import app.promptlang.dto.GeneratedCodeResponse;
import app.promptlang.service.ChatService;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    // due to -method POST, it would look here
    @PostMapping
    public GeneratedCodeResponse chat(@RequestBody ChatRequest request) {
        return chatService.generateResponse(request.getMessage(), request.getUsername());
    }
}