package com.company.hoteledgeservice.controller;

import com.company.hoteledgeservice.service.ServiceLayer;
import com.company.hoteledgeservice.viewmodel.RoomViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HotelController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer service;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // a list of rooms for testing purposes
    private List<RoomViewModel> rooms;

    @Before
    public void setUp() throws Exception {
        // Standard set up method, for instantiating test objects
        // Don't have to do anything special for mockMvc since it's Autowired

        int counter = 1;

        rooms = new ArrayList<>();
        RoomViewModel room = new RoomViewModel();
        room.setId(counter++);
        room.setNumber(101);
        room.setOccupant("vacant");
        rooms.add(room);

        room = new RoomViewModel();
        room.setId(counter++);
        room.setNumber(201);
        room.setOccupant("vacant");
        rooms.add(room);

        room = new RoomViewModel();
        room.setId(counter++);
        room.setNumber(102);
        room.setOccupant("vacant");
        rooms.add(room);

        room = new RoomViewModel();
        room.setId(counter++);
        room.setNumber(202);
        room.setOccupant("vacant");
        rooms.add(room);
    }

    // testing GET /room
    @Test
    public void shouldReturnAllAvailableRooms() throws Exception {
        // Arrange
        // Convert Java objects to JSON

        String outputJson = mapper.writeValueAsString(rooms);

        when(service.getAvailableRooms()).thenReturn(rooms);

        // Act
        mockMvc.perform(get("/room").contentType(MediaType.APPLICATION_JSON)) // perform the get request
                .andDo(print())                     // print result to console
                .andExpect(status().isOk())         // ASSERT (status code is 200)
                .andExpect(content().json(outputJson)); // ASSERT (output is as expected)
    }

    // testing PUT /room/{id}
    @Test
    public void shouldUpdateOccupantAndReturn204StatusCode() throws Exception {
        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        // Arrange
        RoomViewModel room = new RoomViewModel();
        room.setNumber(505);
        room.setOccupant("Star");
        room.setId(11);

        String inputJson = mapper.writeValueAsString(room);

        // Note: will also work with occupant=anyString"
        mockMvc.perform(put("/room/11?occupant=vacant")
                .characterEncoding("UTF-8")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}