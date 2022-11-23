package com.spring.springboot.springbootapplication;

import com.spring.springboot.springbootapplication.controller.CarController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value={"/schema-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CarControllerTest {
    private final MockMvc mockMvc;
    private final CarController carController;

    @Autowired
    public CarControllerTest(final MockMvc mockMvc, final CarController carController) {
        this.mockMvc = mockMvc;
        this.carController = carController;
    }

    @Test
    void getAdditionalInfoShouldReturnStatus200() throws Exception {
        this.mockMvc.perform(get("/shop/1/car/1"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/shop/{shopId}/car/{carId}", 1, 1))
                .andExpect(status().isOk());

    }

}
