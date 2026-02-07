package dev.parkingApp.services;

import dev.parkingApp.dtos.response.ChatResponse;
import dev.parkingApp.mappers.ChatMapper;
import dev.parkingApp.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    
    public List<ChatResponse> getUserChats(Long userId) {
        return chatMapper.toListChatResponses(chatRepository.getUserChats(userId));
    }
    
}
