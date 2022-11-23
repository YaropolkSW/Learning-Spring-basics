package com.spring.springboot.springbootapplication;

import com.spring.springboot.springbootapplication.controller.CarController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("/application-test.properties")
@Sql(value={"/schema-test-start.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value={"/schema-test-end.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CarControllerTest {
    private final MockMvc mockMvc;

    @Autowired
    public CarControllerTest(final MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void getAdditionalInfoShouldReturnStatus200AndExactView() throws Exception {
        this.mockMvc
                .perform(get("/shop/{shopId}/car/{carId}", 1, 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("show-additional-info"));

    }

    @Test
    public void removeCarFromShopByIdShouldReturnStatus3xxAndRedirect() throws Exception {
        this.mockMvc
                .perform(delete("/shop/remove/{shopId}/{carId}", 1, 1))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shop/1"));
    }

    @Test
    public void deleteCarEverywhereShouldReturnStatus3xxAndRedirect() throws Exception {
        this.mockMvc
                .perform(delete("/shop/all_cars/delete/{carId}", 1))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shop/all_cars"));
    }



}
