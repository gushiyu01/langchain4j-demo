package org.gsy.langchaindemo;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.parser.apache.poi.ApachePoiDocumentParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LangchainDemoApplicationTests {

    @Test
    void contextLoads() {
//        should_parse_doc_and_ppt_files("C:\\Users\\admin\\Desktop\\OA_Callback.docx");
    }

    @ParameterizedTest
    @ValueSource(strings = {"C:\\Users\\admin\\Desktop\\OA_Callback.docx"})
    void should_parse_doc_and_ppt_files(String fileName) throws FileNotFoundException {

       try {
           DocumentParser parser = new ApachePoiDocumentParser();
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
           InputStream inputStream = new java.io.FileInputStream("D:\\work\\绩效\\2023年述职报告+研发部+Java工程师+谷世宇.pptx");

           Document document = parser.parse(inputStream);

           assertThat(document.text()).isEqualToIgnoringWhitespace("test content");
           assertThat(document.metadata().toMap()).isEmpty();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }


}
