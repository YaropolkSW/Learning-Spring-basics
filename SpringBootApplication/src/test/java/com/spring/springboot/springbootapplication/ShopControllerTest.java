package com.spring.springboot.springbootapplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/schema-test-start.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/schema-test-end.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCarsInShopShouldReturnStatus200AndExactView() throws Exception {
        mockMvc.perform(get("/shop/{shopId}", 1))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("show-cars-in-shop"));
    }

    @Test
    public void addCarToShopShouldReturnStatus200AndExactView() throws Exception {
        mockMvc.perform(get("/shop/{shopId}/add_car_to_shop", 1))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("add-new-car-in-shop"));
    }

    @Test
    public void saveCarToShopShouldReturnStatus3xxAndRedirect() throws Exception {
        mockMvc.perform(post("/shop/{shopId}/save_car_to_shop", 1)
            .param("car", "Chevrolet Camaro"))
            .andDo(print())
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/shop/1"));
    }

}
