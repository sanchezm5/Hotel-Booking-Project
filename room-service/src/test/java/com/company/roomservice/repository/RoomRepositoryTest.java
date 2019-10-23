package com.company.roomservice.repository;

        import com.company.roomservice.model.Room;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;
        import org.springframework.transaction.annotation.Transactional;
        import java.util.List;
        import java.util.Optional;

        import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomRepositoryTest {

    @Autowired RoomRepository roomRepo;

    @Before
    public void setUp(){
        roomRepo.deleteAll();
    }

    @Test
    @Transactional
    public void addGetDeleteRoom(){

        //Arrange
        Room room = new Room();
        room.setNumber(100);
        room.setOccupant("Jane Smith");

        //Act
        room = roomRepo.save(room);
        Optional<Room> fromRepo = roomRepo.findById(room.getId());

        //Assert
        assertEquals(room, fromRepo.get());

        roomRepo.deleteById(room.getId());
        fromRepo = roomRepo.findById(room.getId());
        assertFalse(fromRepo.isPresent());

    }

    @Test
    public void findAllRooms(){

        Room room1 = new Room();
        room1.setNumber(100);
        room1.setOccupant("Jane Smith");

        roomRepo.save(room1);

        Room room2 = new Room();
        room2.setNumber(101);
        room2.setOccupant("Vacant");

        roomRepo.save(room2);

       List<Room> rooms = roomRepo.findAll();
       assertEquals(rooms.size(), 2);
       assertEquals(rooms.get(0), room1);

    }

    @Test
    @Transactional
    public void updateRoom(){
        //Arrange
        Room room = new Room();
        room.setNumber(100);
        room.setOccupant("Jane Smith");

        roomRepo.save(room);

        room.setOccupant("Vacant");
        roomRepo.save(room);

        Room room1 = roomRepo.getOne(room.getId());
        assertEquals(room, room1);

        //** this achieves the same result;
        //Room room2 = roomRepo.findById(room.getId()).get();
        //assertEquals(room, room2);

    }

}


























