package com.elearningplatform.controller;

import com.elearningplatform.dto.request.ClientReq.LoginClientRequest;
import com.elearningplatform.service.AuthImpl.ClientAuthImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // We mock the service layer to isolate the controller and rate limiter for this test.
    // This prevents the test from actually trying to perform a login.
    @MockBean
    private ClientAuthImpl clientAuthService;

    private String loginRequestBody;

    // Assuming your RateLimiter is configured to allow 10 requests per minute.
    // Adjust this value to match your actual configuration.
    private static final int RATE_LIMIT_PER_MINUTE = 10;

    @BeforeEach
    void setUp() throws Exception {
        LoginClientRequest loginRequest = new LoginClientRequest();
        loginRequest.setEmail("john.me@example.com");
        loginRequest.setPassword("SecurePassword@123");
        loginRequestBody = objectMapper.writeValueAsString(loginRequest);
    }

    @Test
    void whenLoginIsCalledRepeatedly_thenRateLimiterBlocksExcessiveRequests() throws Exception {
        // 1. Consume all available tokens, expecting success (200 OK)
        for (int i = 0; i < RATE_LIMIT_PER_MINUTE; i++) {
            mockMvc.perform(post("/api/v1/client-auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(loginRequestBody))
                    .andExpect(status().isOk());
        }

        // 2. Send one more request, expecting it to be blocked by the rate limiter (429 Too Many Requests)
        mockMvc.perform(post("/api/v1/client-auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestBody))
                .andExpect(status().isTooManyRequests());
    }
}