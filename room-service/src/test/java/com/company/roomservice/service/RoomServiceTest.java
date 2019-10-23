package com.company.roomservice.service;

import com.company.roomservice.model.Room;
import com.company.roomservice.repository.RoomRepository;
import com.company.roomservice.viewmodel.RoomViewModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RoomServiceTest {

    RoomRepository roomRepo;
    RoomService roomService;

    @Before
    public void setUp() throws Exception {
        setUpRoomDaoMock();
        roomService = new RoomService(roomRepo);
    }

    @Test
    public void saveFindFindAllRoom() {
        RoomViewModel roomVM = new RoomViewModel();
        roomVM.setNumber(105);
        roomVM.setOccupant("Jane Doe");

        // Save Room
        roomVM = roomService.saveRoom(roomVM);

        // Find Room
        RoomViewModel fromService = roomService.findRoomById(roomVM.getId());
        assertEquals(roomVM, fromService);

        // Find All
        List<RoomViewModel> roomViewModelList = roomService.findAllRooms();
        assertEquals(2, roomViewModelList.size());
        assertEquals(roomVM, roomViewModelList.get(0));
    }

    @Test
    public void updateRoom() {
        // Arrange
        RoomViewModel roomVM = new RoomViewModel();
        roomVM.setNumber(106);
        roomVM.setOccupant("Jane Doe Update");
        roomVM.setId(5);

        // Act
        roomService.updateRoom(roomVM);
        RoomViewModel updatedRoomFromService = roomService.findRoomById(roomVM.getId());

        // Assert
        assertEquals(roomVM, updatedRoomFromService);
    }

    @Test
    public void removeRoom() {
        // Delete Room
        roomService.removeRoom(6);

        // Find Room
        RoomViewModel deletedRoomVM = roomService.findRoomById(6);

        // Assert
        assertNull(deletedRoomVM);
    }

    private void setUpRoomDaoMock() {
        roomRepo = mock(RoomRepository.class);

        // Request Room
        Room room = new Room();
        room.setNumber(105);
        room.setOccupant("Jane Doe");

        // Response Room
        Room room1 = new Room();
        room1.setNumber(105);
        room1.setOccupant("Jane Doe");
        room1.setId(1);

        // Updated Room
        Room room1Update = new Room();
        room1Update.setNumber(106);
        room1Update.setOccupant("Jane Doe Update");
        room1Update.setId(5);

        Room room2 = new Room();
        room2.setNumber(107);
        room2.setOccupant("John Doe");
        room2.setId(2);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);

        // Delete Room
        Room roomDelete = new Room();
        roomDelete.setNumber(108);
        roomDelete.setOccupant("John Snow");
        roomDelete.setId(6);

        doReturn(room1).when(roomRepo).save(room);
        doReturn(room1).when(roomRepo).getOne(1);
        doReturn(room1Update).when(roomRepo).getOne(5);
        doReturn(rooms).when(roomRepo).findAll();
        doReturn(null).when(roomRepo).getOne(6);
    }
}
