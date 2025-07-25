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
public class ChangeImageToBase64 {

    @Tool(name = "change_image_to_base64", value = "transfer a picture to based64")
    public String generatePicture(@P(value = "image to be transfer") Image prompt) {

        return prompt.base64Data();
    }
}
