package org.gsy.langchaindemo.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.data.image.Image;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

/**
 * @program: langchain4j-demo
 * @description:
 * @author: GSY
 * @create: 2025-07-24 17:16
 **/
public class GeneratePicture {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Tool(name = "generate_picture", value = "Generate a picture based on the given prompt.")
    public Image generatePicture(@P(value = "the prompt to generate") String prompt) {

        try {
            // 构建图片 URL
            String imageUrl = "https://image.pollinations.ai/prompt/" + prompt + "?width=500&height=500&seed=100&model=flux&nologo=true";

            // 创建 HTTP 请求
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(imageUrl))
                    .GET()
                    .build();

            // 发送请求并获取响应
            HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());

            // 检查响应状态码
            if (response.statusCode() == 200) {
                byte[] imageBytes = response.body();
                // 将字节数组转换为 Base64 编码字符串
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                // 构建并返回 Image 对象
                System.out.println(base64Image);
                return Image.builder()
                        .base64Data(base64Image)
                        .build();
            } else {
                throw new RuntimeException("Failed to fetch image, status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while generating picture", e);
        }
    }
}
