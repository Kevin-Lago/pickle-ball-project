package com.kevinthelago.pickle_ball.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinthelago.pickle_ball.dao.Court;
import com.kevinthelago.pickle_ball.dao.Location;
import com.kevinthelago.pickle_ball.service.CourtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.kevinthelago.pickle_ball.util.DocumentGenerator.createTestCourt;
import static com.kevinthelago.pickle_ball.util.Json.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourtController.class)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
public class CourtControllerTests {
    @Autowired
    CourtController courtController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CourtService courtService;

    @Test
    void contextLoads() {
        assertThat(courtController).isNotNull();
    }

    @Test
    void createCourt() throws Exception {
        Court court = createTestCourt();
        when(courtService.create(court)).thenReturn(court);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/courts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(court)))
                .andExpect(status().isCreated());
    }

    @Test
    void createCourts() throws Exception {
        List<Court> courts = IntStream.range(0, 3).mapToObj(val -> createTestCourt()).toList();
        when(courtService.create(courts)).thenReturn(courts);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/courts/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(courts)))
                .andExpect(status().isCreated());
    }

    @Test
    void getCourt() throws Exception {
        Court court = createTestCourt();
        when(courtService.get(court.getUuid())).thenReturn(court);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/courts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(court)))
                .andExpect(status().isOk());
    }

    @Test
    void getCourts() throws Exception {
        List<Court> courts = IntStream.range(0, 3).mapToObj(val -> createTestCourt()).toList();
        when(courtService.get()).thenReturn(courts);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/courts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(courts)))
                .andExpect(status().isOk());
    }

    @Test
    void getCourtsByLocation() throws Exception {
        // ToDo: Test by location
        List<Court> courts = IntStream.range(0, 3).mapToObj(val -> createTestCourt()).toList();
        when(courtService.get()).thenReturn(courts);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/courts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(courts)))
                .andExpect(status().isOk());
    }

    @Test
    void updateCourt() throws Exception {
        Court court = createTestCourt();
        when(courtService.update(court)).thenReturn(court);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/courts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(court)))
                .andExpect(status().isOk());
    }

    @Test
    void updateCourts() throws Exception {
        List<Court> courts = IntStream.range(0, 3).mapToObj(val -> createTestCourt()).toList();
        when(courtService.update(courts)).thenReturn(courts);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/courts/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(courts)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCourt() throws Exception {
        mockMvc.perform(
                        delete("/api/v1/courts", "")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("uuid", UUID.randomUUID().toString()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCourts() throws Exception {
        mockMvc.perform(
                        delete("/api/v1/courts/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("uuids", UUID.randomUUID().toString()))
                .andExpect(status().isOk());
    }
}
