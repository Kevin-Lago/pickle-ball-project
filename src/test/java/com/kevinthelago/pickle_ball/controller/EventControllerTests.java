package com.kevinthelago.pickle_ball.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinthelago.pickle_ball.dao.Event;
import com.kevinthelago.pickle_ball.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerTests {
    @Autowired
    private EventController controller;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventService eventService;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void createEvent() throws Exception {
        Event event = createTestEvent();
        when(eventService.createEvent(event)).thenReturn(event);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(event)))
                .andExpect(status().isCreated());
    }

    @Test
    void createEvents() throws Exception {
        List<Event> events = IntStream.range(0, 3).mapToObj(val -> createTestEvent()).toList();
        when(eventService.createEvents(events)).thenReturn(events);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/events/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(events)))
                .andExpect(status().isCreated());
    }

    @Test
    void getEvent() throws Exception {
        Event event = createTestEvent();
        when(eventService.getEvent(event.getId())).thenReturn(event);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(event)))
                .andExpect(status().isOk());
    }

    @Test
    void getEvents() throws Exception {
        List<Event> events = IntStream.range(0, 3).mapToObj(val -> createTestEvent()).toList();
        when(eventService.getAllEvents()).thenReturn(events);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(events)))
                .andExpect(status().isOk());
    }

    public static Event createTestEvent() {
        return new Event(
            UUID.randomUUID(),
            "",
            "",
            new Date(),
            new Date()
        );
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
