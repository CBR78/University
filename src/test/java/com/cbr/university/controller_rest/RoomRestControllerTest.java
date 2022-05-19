package com.cbr.university.controller_rest;

import com.cbr.university.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomRestControllerTest {
    private static final String URL_ROOMS = "/rest/rooms";

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getTo200ok() {
        ResponseEntity<Room[]> responseEntity = template
                .withBasicAuth("rest", "1122")
                .getForEntity(URL_ROOMS, Room[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
