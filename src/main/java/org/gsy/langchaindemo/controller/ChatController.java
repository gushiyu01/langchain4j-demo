package org.gsy.langchaindemo.controller;

import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.service.Result;
import org.gsy.langchaindemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value="/chatStream", produces = "text/event-stream;charset=utf-8")
    @CrossOrigin
    public Flux<String> chatStream(@RequestParam("message") String message,
                                   @RequestParam("memoryId") String memoryId)  {
        return chatService.chatStream(memoryId,  message);
    }


    @PostMapping("/think-answer")
    public ResponseEntity<ThinkingResponse> getThoughtfulAnswer(
            @RequestParam("memoryId") String memoryId,
            @RequestParam("prompt") String prompt) {

        // 调用服务获取包含思考过程的回答
        Result<String> result = chatService.thinkAndAnswer(memoryId, prompt);

        // 解析响应内容为 ThinkingResponse
        // 这里假设模型会按照特定格式返回内容，如 "思考过程：...\n最终答案：..."
        String content = result.content();
        String[] parts = content.split("\n最终答案：", 2);

        String thoughtProcess = parts.length > 0 ? parts[0].replace("思考过程：", "").trim() : "";
        String finalAnswer = parts.length > 1 ? parts[1].trim() : "";

        return ResponseEntity.ok(new ThinkingResponse(thoughtProcess, finalAnswer));
    }

    // 响应DTO
    public record ThinkingResponse(
            String thoughtProcess, // 思考过程
            String finalAnswer     // 最终回答
    ) {}


}
