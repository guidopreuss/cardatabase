package com.packt.cardatabase;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc() // This disables CSRF and other filters
public class CarRestTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testWithBasicAuthenticationWrongPW() throws Exception {
        this.mockMvc.perform(get("/api/cars")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "falsch")))
                .andDo(print())
                .andExpect(status().isUnauthorized()); // Expecting unauthorized status instead of OK
    }

    @Test
    public void testWithBasicAuthenticationSuccess() throws Exception {
        this.mockMvc.perform(get("/api/cars")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "geheim")))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()); // Expecting unauthorized status instead of OK
    }

}
