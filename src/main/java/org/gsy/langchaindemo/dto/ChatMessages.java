package org.gsy.langchaindemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: langchain4j-demo
 * @description:
 * @author: GSY
 * @create: 2025-08-06 18:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("chat_messages")
public class ChatMessages {
    //唯一标识，映射到 MongoDB 文档的 _id 字段
    @Id
    private ObjectId messageId;
    private String content;
}
