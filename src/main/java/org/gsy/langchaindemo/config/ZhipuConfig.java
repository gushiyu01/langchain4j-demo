package org.gsy.langchaindemo.config;

import dev.langchain4j.community.model.zhipu.ZhipuAiChatModel;
import dev.langchain4j.community.model.zhipu.ZhipuAiStreamingChatModel;
import dev.langchain4j.community.model.zhipu.chat.ChatCompletionModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @program: langchain4j-demo
 * @description:
 * @author: GSY
 * @create: 2025-08-05 09:45
 **/
@Configuration
public class ZhipuConfig {

    @Bean
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "bigModel")
    public ChatLanguageModel zhipuAiChatModel() {
        return ZhipuAiChatModel.builder()
                .model("glm-4.5-flash")
                .apiKey("bb0c6a352dd24cc68a28e53c429bc67c.YvaQJ9nqyjEGpGri")
                .logRequests(true)
                .logResponses(true)
                .maxRetries(1)
                .callTimeout(Duration.ofSeconds(60))
                .connectTimeout(Duration.ofSeconds(60))
                .readTimeout(Duration.ofSeconds(60))
                .writeTimeout(Duration.ofSeconds(60))

                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "bigModel")
    public StreamingChatLanguageModel zhipuAiStreamingChatModel() {
        ZhipuAiStreamingChatModel build = ZhipuAiStreamingChatModel.builder()
                .model(ChatCompletionModel.GLM_4_FLASH)
                .apiKey("bb0c6a352dd24cc68a28e53c429bc67c.YvaQJ9nqyjEGpGri")
                .logRequests(true)
                .logResponses(true)
                .callTimeout(Duration.ofSeconds(60))
                .connectTimeout(Duration.ofSeconds(60))
                .readTimeout(Duration.ofSeconds(60))
                .writeTimeout(Duration.ofSeconds(60))
                .build();
        ChatRequestParameters parameters = build.defaultRequestParameters();
        return build;
    }
}
