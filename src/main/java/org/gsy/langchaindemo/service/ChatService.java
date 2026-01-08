package org.gsy.langchaindemo.service;

import dev.langchain4j.model.input.Prompt;
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

    @SystemMessage("你是一个万能小助手")
    Result<String> chat(@MemoryId String memoryId, @UserMessage String userMessage);

    @SystemMessage(fromResource = "default_user.txt")
    Flux<String> chatStream(@MemoryId String memoryId, @UserMessage String userMessage);
    // 添加新的思考型对话方法
    @SystemMessage(fromResource = "default_user.txt")
    Result<String> thinkAndAnswer(@MemoryId String memoryId, @UserMessage String userMessage);

}
