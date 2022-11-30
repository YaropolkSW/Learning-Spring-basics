package com.spring.springboot.springbootapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring.springboot.springbootapplication.dto.CarDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("/application-test.properties")
@Sql(value = "/schema-test-start.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/schema-test-end.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CarRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAdditionalInfoShouldReturnStatus200AndReturnCorrectJSON() throws Exception {
        final CarDTO carDTO = CarDTO.builder()
            .id(1)
            .brand("Subaru")
            .model("Outback")
            .ageOfProduce(2022)
            .price(6000000)
            .build();

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        final String json = writer.writeValueAsString(carDTO);

        this.mockMvc.perform(get("/api/cars/{id}", 1))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(json));
    }

    @Test
    public void returnCarFromShopByIdShouldReturnStatus200AndCorrectString() throws Exception {
        this.mockMvc.perform(delete("/api/shops/{shopId}/cars/{carId}", 1, 1))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Car with id = 1 was removed from shop Subaru Official Dealer."));
    }

    @Test
    public void deleteCarEverywhereShouldReturnStatus200AndCorrectString() throws Exception {
        this.mockMvc.perform(delete("/api/cars/{carId}", 1))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Car with id = 1 was deleted."));
    }
}
