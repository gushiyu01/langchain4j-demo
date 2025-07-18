package org.gsy.langchaindemo.service;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @program: langchain-demo
 * @description:
 * @author: GSY
 * @create: 2025-07-16 14:01
 **/
@Service
public class ChatServiceFactory {

    @Autowired
    private ContentRetriever contentRetriever;
    @Autowired
    private ChatLanguageModel chatLanguageModel;
    @Autowired
    private StreamingChatLanguageModel streamingChatLanguageModel;

    @Bean
    public ChatService chatService(){

        return AiServices.builder(ChatService.class)
                .chatLanguageModel(chatLanguageModel)
                .streamingChatLanguageModel(streamingChatLanguageModel)
                .contentRetriever(contentRetriever)
                .chatMemoryProvider(memoryId ->  MessageWindowChatMemory.withMaxMessages(100))
//                .chatMemory(messageWindowChatMemory)
                .build();

    }

}
