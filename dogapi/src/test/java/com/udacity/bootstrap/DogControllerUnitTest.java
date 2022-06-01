package com.udacity.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.bootstrap.Entity.Dog;
import com.udacity.bootstrap.service.DogService;
import com.udacity.bootstrap.web.DogController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.mockito.Mockito.doReturn;
import java.io.OutputStream;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value=DogController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class DogControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    DogService dogService;

    @Test
    public void getAllBreeds() throws Exception {
        mockMvc.perform(get("/breeds/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogBreed();
    }

    @Test
    public void getBreed() throws Exception {
        Dog dog = new Dog(1, "Calvin","Terrier", "United Kingdom");
        // Setup our mocked service
        doReturn(objectMapper.writeValueAsString(dog)).when(dogService).retrieveDogBreedById(1);
        MvcResult result = mockMvc.perform(get("/breed/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dog)))
                //.characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(content().string("{\"id\":1,\"name\":\"Calvin\",\"breed\":\"Terrier\",\"origin\":\"United Kingdom\"}"))
                .andExpect(status().isOk())
                .andReturn();

        verify(dogService, times(1)).retrieveDogBreedById(1);

        System.out.println(result.getResponse().getContentAsString());
    }


}

