package com.company.hoteledgeservice.service;

import com.company.hoteledgeservice.util.feign.RoomClient;
import com.company.hoteledgeservice.viewmodel.RoomViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceLayer {

    private RoomClient roomClient;

    @Autowired
    public ServiceLayer(RoomClient roomClient) {
        this.roomClient = roomClient;
    }

    // get available rooms
    public List<RoomViewModel> getAvailableRooms() {
        List<RoomViewModel> rooms = roomClient.getAllRooms();

        List<RoomViewModel> availableRooms = rooms.stream()
                .filter(room -> room.getOccupant().equals("vacant"))
                .collect(Collectors.toList());

        if(availableRooms.size() == 0) {
            throw new IllegalArgumentException("Sorry, there are currently no available rooms at this time");
        }

        return availableRooms;
    }

    // update occupant
    public void updateOccupant(Integer id, String occupant) {
        RoomViewModel room = roomClient.getRoom(id);

        if(room.getOccupant().equals("vacant")) {
            room.setOccupant(occupant);
        }
        else if(!room.getOccupant().equals("vacant")) {
            room.setOccupant("vacant");
        }

        roomClient.updateRoom(room, id);
    }
}
