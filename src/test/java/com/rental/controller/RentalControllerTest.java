package com.rental.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rental.dto.RentalRequestDTO;
import com.rental.service.RentalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RentalController.class)
@ActiveProfiles("test")
public class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService toolsRentalService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void test1() throws Exception {
        String fileName = "test1.json";
        RentalRequestDTO rentalRequestDTO = convertJsonToDTO(fileName);
        assertResponse(rentalRequestDTO);
    }

    private void assertResponse(RentalRequestDTO rentalRequestDTO) throws Exception {
        mockMvc.perform(post("/v1/checkout")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(rentalRequestDTO).toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    private RentalRequestDTO convertJsonToDTO(String fileName) throws IOException {
        ClassPathResource testFile = new ClassPathResource("mockJson/"+ fileName);
        String checkoutRequest = StreamUtils.copyToString( testFile.getInputStream(), Charset.defaultCharset()  );
        RentalRequestDTO rentalRequestDTO = objectMapper.readValue(checkoutRequest, RentalRequestDTO.class);
        return rentalRequestDTO;
    }

    @Test
    public void test2() throws Exception {
        String fileName = "test2.json";
        RentalRequestDTO rentalRequestDTO = convertJsonToDTO(fileName);
        assertResponse(rentalRequestDTO);
    }

    @Test
    public void test3() throws Exception {
        String fileName = "test3.json";
        RentalRequestDTO rentalRequestDTO = convertJsonToDTO(fileName);
        assertResponse(rentalRequestDTO);
    }

    @Test
    public void test4() throws Exception {
        String fileName = "test4.json";
        RentalRequestDTO rentalRequestDTO = convertJsonToDTO(fileName);
        assertResponse(rentalRequestDTO);
    }

    @Test
    public void test5() throws Exception {
        String fileName = "test5.json";
        RentalRequestDTO rentalRequestDTO = convertJsonToDTO(fileName);
        assertResponse(rentalRequestDTO);
    }

    @Test
    public void test6() throws Exception {
        String fileName = "test6.json";
        RentalRequestDTO rentalRequestDTO = convertJsonToDTO(fileName);
        assertResponse(rentalRequestDTO);
    }

}