package com.company.hoteledgeservice.controller;

import com.company.hoteledgeservice.service.ServiceLayer;
import com.company.hoteledgeservice.viewmodel.RoomViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    private ServiceLayer service;

    @Autowired
    public HotelController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/room", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<RoomViewModel> getAvailableRooms() {
        return service.getAvailableRooms();
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOccupant(@PathVariable("id") int id,
                              @RequestParam("occupant") String occupant) {
        service.updateOccupant(id, occupant);
    }
}
