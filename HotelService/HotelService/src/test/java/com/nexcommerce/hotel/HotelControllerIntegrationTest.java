package com.nexcommerce.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexcommerce.hotel.entites.Hotel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(authorities = "Admin")
    public void givenAdminAuthority_whenCreateHotel_thenStatusCreated() throws Exception {
        Hotel newHotel = new Hotel();
        newHotel.setName("Grand NexCommerce Hotel");
        newHotel.setLocation("San Francisco, CA");
        newHotel.setAbout("A premium luxury stay.");

        mockMvc.perform(post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newHotel)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(authorities = "Guest")
    public void givenGuestAuthority_whenCreateHotel_thenStatusForbidden() throws Exception {
        Hotel newHotel = new Hotel();
        newHotel.setName("Hacked Hotel");

        mockMvc.perform(post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newHotel)))
                .andExpect(status().isForbidden());
    }
}
