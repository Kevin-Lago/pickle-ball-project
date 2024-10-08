package com.kevinthelago.pickle_ball.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinthelago.pickle_ball.dao.Event;
import com.kevinthelago.pickle_ball.service.EventService;
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

import static com.kevinthelago.pickle_ball.util.DocumentGenerator.createTestEvent;
import static com.kevinthelago.pickle_ball.util.Json.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
public class EventControllerTests {
    @Autowired
    private EventController controller;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventService eventService;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void createEvent() throws Exception {
        Event event = createTestEvent();
        when(eventService.create(event)).thenReturn(event);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(event)))
                .andExpect(status().isCreated());
    }

    @Test
    void createEvents() throws Exception {
        List<Event> events = IntStream.range(0, 3).mapToObj(val -> createTestEvent()).toList();
        when(eventService.create(events)).thenReturn(events);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/events/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(events)))
                .andExpect(status().isCreated());
    }

    @Test
    void getEvent() throws Exception {
        Event event = createTestEvent();
        when(eventService.get(event.getUuid())).thenReturn(event);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(event)))
                .andExpect(status().isOk());
    }

    @Test
    void getEvents() throws Exception {
        List<Event> events = IntStream.range(0, 3).mapToObj(val -> createTestEvent()).toList();
        when(eventService.get()).thenReturn(events);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(events)))
                .andExpect(status().isOk());
    }

    @Test
    void updateEvent() throws Exception {
        Event event = createTestEvent();
        when(eventService.update(event)).thenReturn(event);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(event)))
                .andExpect(status().isOk());
    }

    @Test
    void updateEvents() throws Exception {
        List<Event> events = IntStream.range(0, 3).mapToObj(val -> createTestEvent()).toList();
        when(eventService.update(events)).thenReturn(events);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/events/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(events)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEvent() throws Exception {
        mockMvc.perform(
                        delete("/api/v1/events", "")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("uuid", UUID.randomUUID().toString()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEvents() throws Exception {
        mockMvc.perform(
                        delete("/api/v1/events/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("uuids", UUID.randomUUID().toString()))
                .andExpect(status().isOk());
    }

}
