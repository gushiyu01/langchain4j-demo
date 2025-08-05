package org.gsy.langchaindemo;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@SpringBootTest
class TikaTests {

    @Test
    void contextLoads() throws IOException {
        Tika tika = new Tika();

        // 检测文件类型
        String fileType = tika.detect(new FileInputStream("D:\\work\\编码规范.docx"));
        System.out.println("文件类型: " + fileType); // 输出: application/pdf

        // 检测字节数组类型
        byte[] data = "Hello World".getBytes();
        String dataType = tika.detect(data);
        System.out.println("数据类型: " + dataType); // 输出: text/plain
    }

    @Test
    void testExtractText() throws IOException, TikaException, SAXException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        try (InputStream stream = new FileInputStream("D:\\work\\编码规范.pdf")) {
            parser.parse(stream, handler, metadata, context);

            System.out.println("PDF元数据:");
            for (String name : metadata.names()) {
                System.out.println(name + " = " + metadata.get(name));
            }

            System.out.println("\nPDF内容:");
            System.out.println(handler.toString());
        }
    }



}
