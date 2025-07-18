package org.gsy.langchaindemo.controller.config;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @program: langchain-demo
 * @description:
 * @author: GSY
 * @create: 2025-07-16 09:56
 **/
@Configuration
public class LangChainConfig {

    @Bean
    public EmbeddingModel embeddingModel() {
        return new AllMiniLmL6V2EmbeddingModel();
    }

    @Bean
    ChatModelListener chatModelListener() {
        return new ChatModelListener() {
            @Override
            public void onRequest(ChatModelRequestContext requestContext) {
                System.out.println("onRequest(): {}"+requestContext.chatRequest());
            }

            @Override
            public void onResponse(ChatModelResponseContext responseContext) {
                System.out.println("onResponse(): {}"+responseContext.chatResponse());
            }

            @Override
            public void onError(ChatModelErrorContext errorContext) {
                System.out.println("onError(): {}"+errorContext.error().getMessage());
            }
        };
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

        TextSegment segment1 = TextSegment.from("太阳系中最大的行星是木星。");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);

        TextSegment segment2 = TextSegment.from("水星是离太阳最近的行星。");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);

        TextSegment segment3 = TextSegment.from("冥王星已经不是太阳系行星。");
        Embedding embedding3 = embeddingModel.embed(segment3).content();
        embeddingStore.add(embedding3, segment3);
        return embeddingStore;
    }

    @Bean
//    @Primary
    public ChatLanguageModel chatModel() {
        ChatLanguageModel openAiChatModel = OpenAiChatModel.builder()
                .apiKey("sk-vwhxyroalhuwmzpposigzidyqbosvbeoopefcahgxpxlpnnn")
                .baseUrl("https://api.siliconflow.cn/v1")
                .modelName("Qwen/QwQ-32B").build();
        return openAiChatModel;
    }

    @Bean
    public StreamingChatLanguageModel streamingChatModel() {

        StreamingChatLanguageModel streamingChatModel = OpenAiStreamingChatModel.builder()
                .apiKey("sk-vwhxyroalhuwmzpposigzidyqbosvbeoopefcahgxpxlpnnn")
                .baseUrl("https://api.siliconflow.cn/v1")
                .logRequests(true)
                .logResponses(true)
                .listeners(List.of(chatModelListener()))
                .modelName("tencent/Hunyuan-A13B-Instruct").build();
//                .modelName("Qwen/Qwen3-8B").build();
        return streamingChatModel;
    }


}
