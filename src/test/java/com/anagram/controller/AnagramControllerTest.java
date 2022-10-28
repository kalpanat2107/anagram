package com.anagram.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AnagramControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Test
    public void whenFileUploaded_thenVerifyStatus()
            throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "abc\nfun\nbac\nfun\ncba\nunf\nhello\nname\n".getBytes()
        );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/api/v1/action/checkAnagram/upload").file(file))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFileUploadedEmpty()
            throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "".getBytes()
        );

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        NestedServletException thrown = assertThrows(
                NestedServletException.class,
                () -> mockMvc.perform(multipart("/api/v1/action/checkAnagram/upload").file(file)),
                "Expected exception"
        );

        assertTrue(thrown.getMessage().contains("File empty"));
    }

    @Test
    public void whenFileUploadedWithNoAnagrams()
            throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "abc\nfun\nhello\nname\n".getBytes()
        );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/api/v1/action/checkAnagram/upload").file(file))
                .andExpect(status().isOk());
    }
    @Test
    public void whenFileUploadedWithSingleChar()
            throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "a\na\na\na\n".getBytes()
        );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/api/v1/action/checkAnagram/upload").file(file))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFileUploadedWithSpecialChars()
            throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "abc%\na%cb\ncba\nbac\n".getBytes()
        );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/api/v1/action/checkAnagram/upload").file(file))
                .andExpect(status().isOk());
    }
}
