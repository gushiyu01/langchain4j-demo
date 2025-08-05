package org.gsy.langchaindemo.tools;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: langchain4j-demo
 * @description:
 * @author: GSY
 * @create: 2025-07-23 17:49
 **/
@Configuration
public class McpConfig {
    @Bean
    public McpToolProvider mcpToolProvider() {
        // 和 MCP 服务通讯
        McpTransport transport = new HttpMcpTransport.Builder()
                // modelscope MCP Fetch网页内容抓取
                .sseUrl("https://mcp.api-inference.modelscope.net/201c7fc3b15244/sse")
                .logRequests(true)
                .logResponses(true)
                .build();
        // 创建 MCP 客户端
        McpClient mcpClient = new DefaultMcpClient.Builder()
                .transport(transport)
                .build();
        // 从 MCP 客户端获取工具
        return McpToolProvider.builder()
                .mcpClients(mcpClient)
                .build();
    }
}
