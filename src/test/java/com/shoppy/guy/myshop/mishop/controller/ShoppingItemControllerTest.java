package com.shoppy.guy.myshop.mishop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppy.guy.myshop.mishop.model.ShoppingItem;
import com.shoppy.guy.myshop.mishop.repository.ShoppingItemRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingItemControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShoppingItemRepository shoppingItemRepository;

    @Test
    public void getShoppingItemById_testsFetchesItem() throws Exception {
        int id = 2;
        String itemName = RandomString.make();
        String itemDesc = RandomString.make();

        // set up
        shoppingItemRepository.save(ShoppingItem.builder()
                .id(id)
                .itemName(itemName)
                .itemDescription(itemDesc)
                .build());



        MockHttpServletRequestBuilder requestBuilder = get("/shoppingitem/"+id)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(id)))
                .andExpect(jsonPath("$.itemName",is(itemName)))
                .andExpect(jsonPath("$.itemDescription",is(itemDesc)))
                .andReturn();
    }

    @Test
    public void getShoppingItems_fetchesAtLeastTwo() throws Exception {


        MockHttpServletRequestBuilder requestBuilder = get("/shoppingitem/")
                .contentType(MediaType.APPLICATION_JSON);

        // complete validation of size 2
        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteShoppingItems_deletesOne() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = get("/shoppingitem/")
                .contentType(MediaType.APPLICATION_JSON);

        // complete validation of size 2
        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletRequestBuilder requestBuilderDel = delete("/shoppingitem/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilderDel)
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void putShoppingItems_addsOne() throws Exception {

        ShoppingItem item = ShoppingItem.builder()
                .itemName("Jeaqueolatte-Rings")
                .itemDescription("Chocolatey rings")
                .price("100")
                .build();

        MockHttpServletRequestBuilder requestBuilder = get("/shoppingitem/")
                .contentType(MediaType.APPLICATION_JSON);

        // complete validation of size 2
        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletRequestBuilder requestBuilderPut = put("/shoppingitem/")
                .content(new ObjectMapper().writeValueAsString(item))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilderPut)
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(status().isOk())
                .andReturn();

    }
}
