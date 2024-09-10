package com.kevinthelago.pickle_ball.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinthelago.pickle_ball.dao.Court;
import com.kevinthelago.pickle_ball.dao.Location;
import com.kevinthelago.pickle_ball.service.CourtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourtController.class)
@ActiveProfiles(value = "test")
public class CourtControllerSecureTests {
    @Autowired
    private CourtController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CourtService courtService;

    @Test
    void createCourt() throws Exception {
        Court court = createTestCourt();
        when(courtService.create(court)).thenReturn(court);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/courts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(court)))
                .andExpect(status().isForbidden());
    }

    @Test
    void createCourts() throws Exception {
        List<Court> courts = IntStream.range(0, 3).mapToObj(val -> createTestCourt()).toList();
        when(courtService.create(courts)).thenReturn(courts);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/courts/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(courts)))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateCourt() throws Exception {
        Court court = createTestCourt();
        when(courtService.update(court)).thenReturn(court);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/courts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(court)))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateCourts() throws Exception {
        List<Court> courts = IntStream.range(0, 3).mapToObj(val -> createTestCourt()).toList();
        when(courtService.update(courts)).thenReturn(courts);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/courts/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(courts)))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteCourt() throws Exception {
        Court court = createTestCourt();

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/courts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(court)))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteCourts() throws Exception {
        List<Court> courts = IntStream.range(0, 3).mapToObj(val -> createTestCourt()).toList();

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/courts/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(courts)))
                .andExpect(status().isForbidden());
    }
}
