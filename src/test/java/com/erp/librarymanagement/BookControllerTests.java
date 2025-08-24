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
    void register_and_list() throws Exception {
        var req = new BookRequest();
        req.setIsbnNo("123"); req.setTitle("Title"); req.setAuthor("Author");

        mvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isbn").value("123"));

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].available").value(true));
    }

}
