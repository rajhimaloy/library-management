package com.erp.librarymanagement;

import com.erp.librarymanagement.model.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * Author: Rajib Kumer Ghosh
 */

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    @Test
    void registerAndList() throws Exception {
        var req = new BookDTO();
        req.setIsbnNo("100001"); req.setTitle("Java SE"); req.setAuthor("Rajib Kumer Ghosh");

        mvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isbn").value("100001"));

        mvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].available").value(true));
    }

}
