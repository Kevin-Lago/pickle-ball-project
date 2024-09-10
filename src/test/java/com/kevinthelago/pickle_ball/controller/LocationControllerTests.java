package com.kevinthelago.pickle_ball.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinthelago.pickle_ball.dao.Location;
import com.kevinthelago.pickle_ball.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.kevinthelago.pickle_ball.util.DocumentGenerator.createTestLocation;
import static com.kevinthelago.pickle_ball.util.Json.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocationController.class)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
public class LocationControllerTests {
    @Autowired
    private LocationController controller;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LocationService locationService;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void createLocation() throws Exception {
        Location location = createTestLocation();
        when(locationService.create(location)).thenReturn(location);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/locations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(location)))
                .andExpect(status().isCreated());
    }

    @Test
    void createLocations() throws Exception {
        List<Location> locations = IntStream.range(0, 3).mapToObj(val -> createTestLocation()).toList();
        when(locationService.create(locations)).thenReturn(locations);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/locations/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(locations)))
                .andExpect(status().isCreated());
    }

    @Test
    void getLocation() throws Exception {
        Location location = createTestLocation();
        when(locationService.get(location.getUuid())).thenReturn(location);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/locations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(location)))
                .andExpect(status().isOk());
    }

    @Test
    void getLocations() throws Exception {
        List<Location> locations = IntStream.range(0, 3).mapToObj(val -> createTestLocation()).toList();
        when(locationService.get()).thenReturn(locations);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/locations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(locations)))
                .andExpect(status().isOk());
    }

    @Test
    void updateLocation() throws Exception {
        Location location = createTestLocation();
        when(locationService.update(location)).thenReturn(location);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/locations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(location)))
                .andExpect(status().isOk());
    }

    @Test
    void updateLocations() throws Exception {
        List<Location> locations = IntStream.range(0, 3).mapToObj(val -> createTestLocation()).toList();
        when(locationService.update(locations)).thenReturn(locations);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/locations/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(locations)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteLocation() throws Exception {
        mockMvc.perform(
                        delete("/api/v1/locations", "")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("uuid", UUID.randomUUID().toString()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteLocations() throws Exception {
        mockMvc.perform(
                        delete("/api/v1/locations/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("uuids", UUID.randomUUID().toString()))
                .andExpect(status().isOk());
    }
}
