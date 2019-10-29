package com.company.roomservice.controller;

import com.company.roomservice.model.Room;
import com.company.roomservice.service.RoomService;
import com.company.roomservice.viewmodel.RoomViewModel;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)

public class RoomControllerTest {

    //wiring in the MockMVC object
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService service;

    //ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    //a List of Rooms for testing purposes
    private List<RoomViewModel> rooms;


    @Before
    public void setUp(){
        // Standard set up method, for instantiating test objects
        // Don't have to do anything special for mockMvc since it's Autowired

        int counter = 1;

        rooms = new ArrayList<>();
        RoomViewModel room = new RoomViewModel();
        room.setId(counter++);
        room.setNumber(101);
        room.setOccupant("Abraham");
        rooms.add(room);

        room = new RoomViewModel();
        room.setId(counter++);
        room.setNumber(201);
        room.setOccupant("Betsy");
        rooms.add(room);

        room = new RoomViewModel();
        room.setId(counter++);
        room.setNumber(102);
        room.setOccupant("Rocky");
        rooms.add(room);

        room = new RoomViewModel();
        room.setId(counter++);
        room.setNumber(202);
        room.setOccupant("Linda");
        rooms.add(room);



    }

    //testing GET /room
    @Test
    public void shouldReturnAllRooms() throws Exception {
        // ARRANGE
        // Convert Java object to JSON

        String outputJson = mapper.writeValueAsString(rooms);

        when(service.findAllRooms()).thenReturn(rooms);

        //Act
        mockMvc.perform(get("/room").contentType(MediaType.APPLICATION_JSON))  //perform the get request

                .andDo(print())                  //print result to console
                .andExpect(status().isOk())     //ASSERT (status code is 200)
                .andExpect(content().json(outputJson));  //ASSERT (output is as expected)
    }


    //Testing POST /room
    @Test
    public void shouldReturnNewRoomOnPostRequest() throws Exception {
        //Arrange
        RoomViewModel newRoom = new RoomViewModel();
        newRoom.setNumber(505);
        newRoom.setOccupant("Ozzy");

        //convert Java object to JSON
        String inputJson = mapper.writeValueAsString(newRoom);

        RoomViewModel newRoomReturn = new RoomViewModel();
        newRoomReturn.setNumber(505);
        newRoomReturn.setOccupant("Ozzy");
        newRoomReturn.setId(10);


        String outputJson = mapper.writeValueAsString(newRoomReturn);

        when(service.saveRoom(newRoom)).thenReturn(newRoomReturn);

        mockMvc.perform(
                post("/room")
                .content(inputJson).characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    // testing GET /room/{id}
    @Test
    public void shouldReturnRoomById() throws Exception{

        //Arrange
        RoomViewModel room = new RoomViewModel();
        room.setNumber(505);
        room.setOccupant("Ozzy");
        room.setId(10);

        String outputJson = mapper.writeValueAsString(room);

        when(service.findRoomById(10)).thenReturn(room);

        //How do we test for a room that does not exist?
        //when(service.findRoomById(11)).thenReturn(null);


        mockMvc.perform(get("/room/10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    //testing PUT /room/{id}
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception{

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content


        //Arrange
        RoomViewModel room = new RoomViewModel();
        room.setNumber(505);
        room.setOccupant("Star");
        room.setId(11);

        String inputJson = mapper.writeValueAsString(room);




        mockMvc.perform(put("/room/11")
                .characterEncoding("UTF-8")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // testing DELETE /room/{id}

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception{
        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        mockMvc.perform(delete("/room/13"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}

















