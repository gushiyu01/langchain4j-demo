package org.gsy.langchaindemo.controller;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import reactor.core.publisher.Flux;

/**
 * @program: langchain-demo
 * @description:
 * @author: GSY
 * @create: 2025-07-16 14:01
 **/
public interface ChatService {

    @SystemMessage("你是一个天文学家")
    Result<String> chat(@MemoryId String memoryId, @UserMessage String userMessage);

    @SystemMessage("你是一个天文学家")
    Flux<String> chatStream(@MemoryId String memoryId, @UserMessage String userMessage);
}
