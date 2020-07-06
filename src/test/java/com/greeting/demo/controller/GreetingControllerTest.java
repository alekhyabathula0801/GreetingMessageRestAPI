package com.greeting.demo.controller;

import com.google.gson.Gson;
import com.greeting.demo.exception.GreetingException;
import com.greeting.demo.model.Greeting;
import com.greeting.demo.model.User;
import com.greeting.demo.service.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GreetingController.class)
public class GreetingControllerTest {

    @MockBean
    GreetingService greetingService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void givenUrlToGetGreeting_whenValid_shouldReturnResults() {
        try {
            when(greetingService.findGreeting((long) 1)).thenReturn(new Greeting(1,"hello world"));
            Gson gson = new Gson();
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/greeting/1")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("fail",HttpStatus.OK.value(),result.getResponse().getStatus());
            JSONAssert.assertEquals(gson.toJson(new Greeting(1,"hello world")),result.getResponse().getContentAsString(),false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUrlToGetGreeting_whenNoDataFound_shouldReturnBadRequest() {
        try {
            when(greetingService.findGreeting((long) 1)).thenThrow(new GreetingException("Greeting with Id  1  not found "));
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/greeting/1")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("fail",HttpStatus.BAD_REQUEST.value(),result.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUrlToGetAllGreetings_whenValid_shouldReturnResults() {
        try {
            List<Greeting> greetings = new ArrayList<>();
            greetings.add(new Greeting(1,"hello world"));
            when(greetingService.findAllGreetings()).thenReturn(greetings);
            Gson gson = new Gson();
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/greeting")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            JSONAssert.assertEquals(gson.toJson(greetings),result.getResponse().getContentAsString(),false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUrlToPostGreeting_whenValid_shouldReturnResults() {
        try {
            when(greetingService.saveGreeting(new User("Alekhya","B"))).thenReturn(new Greeting(1,"hello 'Alekhya B' !"));
            Gson gson = new Gson();
            RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/greeting")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .content(gson.toJson(new User("Alekhya","B")))
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("fail",HttpStatus.OK.value(),result.getResponse().getStatus());
            JSONAssert.assertEquals(gson.toJson(new Greeting(1,"hello 'Alekhya B' !")),result.getResponse().getContentAsString(),false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUrlToPostGreeting_whenNoValidData_shouldReturnResults() {
        try {
            Gson gson = new Gson();
            RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/greeting")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .content(gson.toJson(new User("","B")))
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("fail",HttpStatus.BAD_REQUEST.value(),result.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUrlToPostGreeting_whenNullData_shouldReturnResults() {
        try {
            Gson gson = new Gson();
            RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/greeting")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .content(gson.toJson(new User(null,"B")))
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("fail",HttpStatus.BAD_REQUEST.value(),result.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUrlToDeleteGreeting_whenValid_shouldReturnResults() {
        try {
            doNothing().when(greetingService).deleteGreeting((long) 1);
            RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/greeting/1")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("fail",HttpStatus.OK.value(),result.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUrlToDeleteAllGreetings_whenValid_shouldReturnResults() {
        try {
            doNothing().when(greetingService).deleteAllGreetings();
            RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/greeting")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("fail",HttpStatus.OK.value(),result.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUrlToDeleteGreeting_whenNotValid_shouldReturnResults() {
        try {
            doThrow(new GreetingException("Greeting with Id  1  not found ")).when(greetingService).deleteGreeting((long) 1);
            RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/greeting/1")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("fail",HttpStatus.BAD_REQUEST.value(),result.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUrlToPutGreeting_whenValid_shouldReturnResults() {
        try {
            when(greetingService.updateGreeting((long) 1,new User("Alekhya","B"))).thenReturn(new Greeting(1,"hello 'Alekhya B' !"));
            Gson gson = new Gson();
            RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/greeting/1")
                                                                  .accept(MediaType.APPLICATION_JSON)
                                                                  .content(gson.toJson(new User("Alekhya","B")))
                                                                  .contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            assertEquals("fail",HttpStatus.OK.value(),result.getResponse().getStatus());
            JSONAssert.assertEquals(gson.toJson(new Greeting(1,"hello 'Alekhya B' !")),result.getResponse().getContentAsString(),false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
