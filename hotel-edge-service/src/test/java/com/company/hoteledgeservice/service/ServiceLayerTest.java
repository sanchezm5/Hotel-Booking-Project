package com.company.hoteledgeservice.service;

import com.company.hoteledgeservice.util.feign.RoomClient;
import com.company.hoteledgeservice.viewmodel.RoomViewModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    ServiceLayer service;
    RoomClient roomClient;

    @Before
    public void setUp() throws Exception {
        setUpRoomClientMock();
        service = new ServiceLayer(roomClient);
    }

    @Test
    public void getAvailableRooms() {
        List<RoomViewModel> fromService = service.getAvailableRooms();
        assertEquals(fromService.size(), 1);
    }

    @Test
    public void updateOccupant() {
        // Arrange
        RoomViewModel room = new RoomViewModel();
        room.setId(11);
        room.setNumber(508);
        room.setOccupant("Joe Warren");

        // Act
        service.updateOccupant(11, "vacant");
        RoomViewModel fromService = roomClient.getRoom(11);

        // Assert
        assertFalse(room.equals(fromService));
    }

    public void setUpRoomClientMock() {
        roomClient = mock(RoomClient.class);

        List<RoomViewModel> rooms = new ArrayList<>();

        RoomViewModel room = new RoomViewModel();
        room.setId(10);
        room.setNumber(507);
        room.setOccupant("vacant");
        rooms.add(room);

        room = new RoomViewModel();
        room.setId(20);
        room.setNumber(907);
        room.setOccupant("MaryJane");
        rooms.add(room);

        doReturn(rooms).when(roomClient).getAllRooms();
        doReturn(room).when(roomClient).getRoom(10);

        //set up update mock
        RoomViewModel room1 = new RoomViewModel();
        room1.setId(11);
        room1.setNumber(508);
        room1.setOccupant("Joe Warren");

        doNothing().when(roomClient).updateRoom(room1, 11);
        doReturn(room1).when(roomClient).getRoom(11);
    }
}