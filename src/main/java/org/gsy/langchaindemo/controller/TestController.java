package org.gsy.langchaindemo.controller;

import com.alibaba.excel.EasyExcel;
import dev.langchain4j.service.Result;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.gsy.langchaindemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import reactor.core.publisher.Flux;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: langchain-demo
 * @description: .
 * @author: GSY
 * @create: 2025-07-15 17:34
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping(value="/file")
    @CrossOrigin
    public String file(@RequestParam("file") MultipartFile file) throws IOException {

        Tika tika = new Tika();
        String fileType = tika.detect(file.getBytes());
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        try  {
            parser.parse(file.getInputStream(), handler, metadata, context);

            System.out.println("PDF元数据:");
            for (String name : metadata.names()) {
                System.out.println(name + " = " + metadata.get(name));
            }

            System.out.println("\nPDF内容:");
            System.out.println(handler.toString());
        } catch (TikaException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return fileType;
    }

    @GetMapping("/getUrl")
    public String test(@RequestParam("url") String url) {
        StringBuilder sb = new StringBuilder();

        sb.append("<a&nbsp;href=\"").append(url).append("\">").append(url).append("</a>");
        return sb.toString();
    }

    //读取excel文件
    @PostMapping("/excel")
    public List<String> excel(@RequestParam("file") MultipartFile file) throws IOException {
        //easyexcel获取excel内容
        List<DemoData> dataList = EasyExcel.read(file.getInputStream()).head(DemoData.class).sheet().doReadSync();
        List<String> list = new ArrayList<>();
        for (DemoData data : dataList) {
            //详情请点击“企业职工退休一件事”办理流程？（http://www.zzsi.com/site-data/sbzx/html/2023/04/23/1682236555213.html） 将url提取出来
            if (data.getEmail()=="" || data.getEmail()==null)
                continue;
            String url = data.getEmail().substring(data.getEmail().indexOf("（")+1, data.getEmail().indexOf("）"));
            StringBuilder sb = new StringBuilder();
            sb.append("<a&nbsp;href=\"").append(url).append("\">").append(url).append("</a>");
            System.out.println(data.getId() + "、" + data.getName() + "\n" + data.getAge() +"，" +sb.toString()+"#@");
            list.add(data.getId() + "、" + data.getName() + "\n" + data.getAge() +"，" +url);
        }
        return list;
    }
}
