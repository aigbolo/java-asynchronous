package io.devpers.asynchronous.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SimpleControllerTest {
    Logger logger = LoggerFactory.getLogger(SimpleControllerTest.class);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SimpleController simpleController;

    @Test
    public void whenTestHelloMethod_thenRetrieveExpectedStatus() throws Exception{
        logger.debug("whenTestHelloMethod_thenRetrieveExpectedStatus");
        this.mockMvc.perform(get("/simple/hello"))
                .andExpect(status().isAccepted())
        .andExpect(content().string(containsString("Hello Bob")));
    }

//    @Test
//    public void whenTestHelloMethod_thenRetrieveExpectedResult() throws Exception{
//        logger.debug("whenTestHelloMethod_thenRetrieveExpectedResult");
//        ResponseEntity<List<String>> response = this.simpleController.simple();
//        assertEquals(response.getBody(),"Hello");
//    }


}
