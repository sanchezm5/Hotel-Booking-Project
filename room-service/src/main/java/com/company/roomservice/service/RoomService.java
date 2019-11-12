package com.company.roomservice.service;

import com.company.roomservice.model.Room;
import com.company.roomservice.repository.RoomRepository;
import com.company.roomservice.viewmodel.RoomViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private RoomRepository roomRepo;

    @Autowired
    public RoomService(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Transactional
    public RoomViewModel saveRoom(RoomViewModel roomVM) {
        Room room = new Room();
        room.setNumber(roomVM.getNumber());
        room.setOccupant(roomVM.getOccupant());
        room = roomRepo.save(room);

        roomVM.setId(room.getId());
        return roomVM;
    }

    public RoomViewModel findRoomById(int id) {

        Room room = roomRepo.getOne(id);

        if (room == null) {
            return null;
        } else {
            return buildRoomViewModel(room);
        }



    }

    public List<RoomViewModel> findAllRooms() {
        List<Room> rooms = roomRepo.findAll();
        List<RoomViewModel> roomViewModels = new ArrayList<>();

        for (Room room : rooms) {
            RoomViewModel roomViewModel = buildRoomViewModel(room);
            roomViewModels.add(roomViewModel);
        }
        return roomViewModels;
    }

    @Transactional
    public void updateRoom(RoomViewModel roomVM) {
        Room room = new Room();
        room.setId(roomVM.getId());
        room.setNumber(roomVM.getNumber());
        room.setOccupant(roomVM.getOccupant());
        roomRepo.save(room);
    }

    @Transactional
    public void removeRoom(int id) {
        roomRepo.deleteById(id);
    }

    private RoomViewModel buildRoomViewModel(Room room) {
        RoomViewModel roomViewModel = new RoomViewModel();
        roomViewModel.setId(room.getId());
        roomViewModel.setNumber(room.getNumber());
        roomViewModel.setOccupant(room.getOccupant());

        return roomViewModel;
    }


}
