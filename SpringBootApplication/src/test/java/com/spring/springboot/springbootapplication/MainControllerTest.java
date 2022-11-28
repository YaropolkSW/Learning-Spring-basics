package com.spring.springboot.springbootapplication;

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
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllShopsShouldReturnStatus200AndExactView() throws Exception {
        mockMvc.perform(get("/shop"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("show-all-shops"));
    }

    @Test
    public void getAllCarsShouldReturnStatus200AndExactView() throws Exception {
        mockMvc.perform(get("/shop/all_cars"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("show-all-cars"));
    }

    @Test
    public void addNewCarShouldReturnStatus200AndExactView() throws Exception {
        mockMvc.perform(get("/shop/all_cars/add_new_car"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("add-new-car"));
    }

    @Test
    public void saveNewCarShouldReturnStatus3xxAndRedirect() throws Exception {
        final CarDTO carDTO = CarDTO.builder()
            .brand("Subaru")
            .model("XV")
            .ageOfProduce(2022)
            .price(4000000)
            .build();

        mockMvc.perform(post("/shop/all_cars/save_new_car").flashAttr("car", carDTO))
            .andDo(print())
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/shop/all_cars"));
    }
}
