package org.gsy.langchaindemo.tools;

import dev.langchain4j.agent.tool.Tool;

/**
 * @program: langchain4j-demo
 * @description:
 * @author: GSY
 * @create: 2025-08-18 10:05
 **/
public class LogTool {
    @Tool("记录推理过程")
    public String logReasoning(String thoughts) {
        // 可存储到数据库或日志
        System.out.println("推理过程: " + thoughts);
        return thoughts;
    }
}
