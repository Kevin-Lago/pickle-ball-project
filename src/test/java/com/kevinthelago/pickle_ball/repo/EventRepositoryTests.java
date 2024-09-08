package com.kevinthelago.pickle_ball.repo;

import com.kevinthelago.pickle_ball.dao.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EventRepositoryTests {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    TestEntityManager entityManager;

//    @Test
//    void givenNewCampaign_whenSave_thenSuccess() {
//        Event event = new Event(
//                UUID.randomUUID(),
//                "",
//                "",
//                new Date(),
//                new Date()
//        );
//        Event insertedEvent = eventRepository.save(event);
//        assertThat(entityManager.find(Event.class, insertedEvent.getUuid())).isEqualTo(event);
//    }
//
//    @Test
//    void givenCampaignCreated_whenFindById_thenSuccess() {
//        Event event = new Event(
//                UUID.randomUUID(),
//                "",
//                "",
//                new Date(),
//                new Date()
//        );
//        entityManager.persist(event);
//        Optional<Event> retrievedCampaign = eventRepository.findById(event.getUuid());
//        assertThat(retrievedCampaign).contains(event);
//    }
}
