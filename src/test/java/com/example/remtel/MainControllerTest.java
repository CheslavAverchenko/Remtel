package com.example.remtel;

import com.example.remtel.controllers.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("Cheslav")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/forms-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/forms-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController controller;

    @Test
    public void mainPageTest() throws Exception{
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='navbarSupportedContent']/div").string("Cheslav"));
    }
    @Test
    public void formListTest() throws Exception{
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='forms-list']/div").nodeCount(4));
    }
    @Test
    public void filterFormTest() throws Exception{
        this.mockMvc.perform(get("/main").param("filter","samsung"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='forms-list']/div").nodeCount(1))
                .andExpect(xpath("//div[@id='forms-list']/div[@data-id=2]").exists());
    }

    @Test
    public void addFormToListTest() throws Exception{
        MockHttpServletRequestBuilder multipart =  multipart("/main")
                .file("file","123".getBytes())
                .param("id","10")
                .param("message","fifth")
                .param("subject","hp")
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='forms-list']/div").nodeCount(5))
                .andExpect(xpath("//div[@id='forms-list']/div[@data-id='46']/div/strong").string("hp"))
                .andExpect(xpath("//div[@id='forms-list']/div[@data-id='46']/div/span").string("fifth"));

    }
}
//.param("date","2021-03-27")
//.andExpect(xpath("//div[@id='forms-list']/div[@data-id='10']/div/strong").string("2021-03-27"))