package com.kevinthelago.pickle_ball.service;

import com.kevinthelago.pickle_ball.controller.CourtController;
import com.kevinthelago.pickle_ball.dao.Court;
import com.kevinthelago.pickle_ball.dao.Location;
import com.kevinthelago.pickle_ball.repo.CourtRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(CourtController.class)
@ActiveProfiles(value = "test")
public class CourtServiceTests {
    @MockBean
    CourtRepository courtRepository;
    @Autowired
    CourtService courtService;

    @Test
    public void createCourtTest() {
        Court court = new Court(
                UUID.randomUUID(),
                1,
                new Location()
        );
        when(courtRepository.save(court)).thenReturn(court);

//        assertThat(courtService.create(court)).
    }
}
