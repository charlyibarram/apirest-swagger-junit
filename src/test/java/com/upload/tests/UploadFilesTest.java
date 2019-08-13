/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upload.tests;

import com.upload.controller.UploadFileController;
import com.upload.service.RepositoryFile;
import java.io.InputStream;
import java.util.ArrayList;
import javax.ws.rs.core.MediaType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author cibarra
 */
@RunWith(MockitoJUnitRunner.class)
public class UploadFilesTest {

    private InputStream is;
    private MockMvc mockMvc;

    @Mock
    private RepositoryFile repositoryFile;

    @Spy
    @InjectMocks
    private UploadFileController controller = new UploadFileController();

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        is = controller.getClass().getClassLoader().getResourceAsStream("test.txt");
    }

    @Test
    public void testUploadFile() throws Exception {
        Mockito.when(repositoryFile.findAll()).thenReturn(new ArrayList<>());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/getMetaData").contentType(MediaType.APPLICATION_XML)).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals("application/xml;charset=UTF-8", result.getResponse().getContentType());
    }

}
