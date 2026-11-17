package com.decorator.controller;

import com.decorator.service.CoffeeBuilderService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CoffeeControllerTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(
            new CoffeeController(new CoffeeBuilderService())
    ).build();

    @Test
    void invalidBaseReturns400RatherThanUnexpectedServerError() throws Exception {
        mvc.perform(post("/api/coffee/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"baseType\":\"unknown\",\"addons\":[]}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }
}
