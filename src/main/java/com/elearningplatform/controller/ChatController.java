package com.elearningplatform.controller;

import com.elearningplatform.data.model.Chat;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.data.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
@AllArgsConstructor
public class ChatController {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody Chat chatMessage) {
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiver().toString(),
                "/queue/messages",
                chatMessage
        );
        return "Message sent successfully to user " + chatMessage.getReceiver();
    }
    }

