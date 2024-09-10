package com.kevinthelago.pickle_ball.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinthelago.pickle_ball.dao.Event;
import com.kevinthelago.pickle_ball.service.EventService;
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

import static com.kevinthelago.pickle_ball.util.DocumentGenerator.createTestEvent;
import static com.kevinthelago.pickle_ball.util.Json.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
@ActiveProfiles(value = "test")
public class EventControllerSecureTests {
    @Autowired
    private EventController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventService eventService;

    @Test
    void createEvent() throws Exception {
        Event event = createTestEvent();
        when(eventService.create(event)).thenReturn(event);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(event)))
                .andExpect(status().isForbidden());
    }

    @Test
    void createEvents() throws Exception {
        List<Event> events = IntStream.range(0, 3).mapToObj(val -> createTestEvent()).toList();
        when(eventService.create(events)).thenReturn(events);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/events/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(events)))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateEvent() throws Exception {
        Event event = createTestEvent();
        when(eventService.update(event)).thenReturn(event);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(event)))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateEvents() throws Exception {
        List<Event> events = IntStream.range(0, 3).mapToObj(val -> createTestEvent()).toList();
        when(eventService.update(events)).thenReturn(events);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/events/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(events)))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteEvent() throws Exception {
        Event event = createTestEvent();

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(event)))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteEvents() throws Exception {
        List<Event> events = IntStream.range(0, 3).mapToObj(val -> createTestEvent()).toList();

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/events/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(events)))
                .andExpect(status().isForbidden());
    }
}
