package com.company.hoteledgeservice.service;

import com.company.hoteledgeservice.util.feign.RoomClient;
import com.company.hoteledgeservice.viewmodel.RoomViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLayer {

    private RoomClient roomClient;

    @Autowired
    public ServiceLayer(RoomClient roomClient) {
        this.roomClient = roomClient;
    }

    // get available rooms
    public List<RoomViewModel> getAvailableRooms() {
        return null;
    }

    // update occupant
    public void updateOccupant(Integer id, String occupant) {

    }

}
