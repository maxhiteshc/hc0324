package com.toolsrental.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolsrental.demo.controller.ToolsRentalController;
import com.toolsrental.demo.dto.ToolsRentalRequestDTO;
import com.toolsrental.demo.service.ToolsRentalService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ToolsRentalController.class)
@ActiveProfiles("test")
public class ToolsRentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToolsRentalService toolsRentalService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void test1() throws Exception {
        ClassPathResource testFile = new ClassPathResource("mockJson/test1.json");
        String checkoutRequest = StreamUtils.copyToString( testFile.getInputStream(), Charset.defaultCharset()  );
        ToolsRentalRequestDTO toolsRentalRequestDTO = objectMapper.readValue(checkoutRequest, ToolsRentalRequestDTO.class);
        mockMvc.perform(post("/v1/checkout")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(toolsRentalRequestDTO).toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void test2() throws Exception {
        ClassPathResource testFile = new ClassPathResource("mockJson/test2.json");
        String checkoutRequest = StreamUtils.copyToString( testFile.getInputStream(), Charset.defaultCharset()  );
        ToolsRentalRequestDTO toolsRentalRequestDTO = objectMapper.readValue(checkoutRequest, ToolsRentalRequestDTO.class);
        mockMvc.perform(post("/v1/checkout")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(toolsRentalRequestDTO).toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void test3() throws Exception {
        ClassPathResource testFile = new ClassPathResource("mockJson/test3.json");
        String checkoutRequest = StreamUtils.copyToString( testFile.getInputStream(), Charset.defaultCharset()  );
        ToolsRentalRequestDTO toolsRentalRequestDTO = objectMapper.readValue(checkoutRequest, ToolsRentalRequestDTO.class);
        mockMvc.perform(post("/v1/checkout")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(toolsRentalRequestDTO).toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void test4() throws Exception {
        ClassPathResource testFile = new ClassPathResource("mockJson/test4.json");
        String checkoutRequest = StreamUtils.copyToString( testFile.getInputStream(), Charset.defaultCharset()  );
        ToolsRentalRequestDTO toolsRentalRequestDTO = objectMapper.readValue(checkoutRequest, ToolsRentalRequestDTO.class);
        mockMvc.perform(post("/v1/checkout")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(toolsRentalRequestDTO).toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void test5() throws Exception {
        ClassPathResource testFile = new ClassPathResource("mockJson/test5.json");
        String checkoutRequest = StreamUtils.copyToString( testFile.getInputStream(), Charset.defaultCharset()  );
        ToolsRentalRequestDTO toolsRentalRequestDTO = objectMapper.readValue(checkoutRequest, ToolsRentalRequestDTO.class);
        mockMvc.perform(post("/v1/checkout")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(toolsRentalRequestDTO).toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void test6() throws Exception {
        ClassPathResource testFile = new ClassPathResource("mockJson/test6.json");
        String checkoutRequest = StreamUtils.copyToString( testFile.getInputStream(), Charset.defaultCharset()  );
        ToolsRentalRequestDTO toolsRentalRequestDTO = objectMapper.readValue(checkoutRequest, ToolsRentalRequestDTO.class);
        mockMvc.perform(post("/v1/checkout")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(toolsRentalRequestDTO).toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

}