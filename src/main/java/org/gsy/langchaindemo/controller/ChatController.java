package org.gsy.langchaindemo.controller;

import dev.langchain4j.service.Result;
import org.gsy.langchaindemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @program: langchain-demo
 * @description: .
 * @author: GSY
 * @create: 2025-07-15 17:34
 **/
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/chat")
    public String model(
            @RequestParam("memoryId") String memoryId,
            @RequestParam("message") String message
            ) {
        Result<String> chat = chatService.chat(memoryId, message);
        return chat.content();
    }

    @GetMapping(value="/chatStream")
    @CrossOrigin
    public Flux<String> chatStream(@RequestParam("message") String message,
                                   @RequestParam("memoryId") String memoryId)  {
        return chatService.chatStream(memoryId,  message);
    }
}
