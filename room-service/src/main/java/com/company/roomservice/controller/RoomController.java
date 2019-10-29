package com.company.roomservice.controller;

import com.company.roomservice.exception.NotFoundException;
import com.company.roomservice.model.Room;
import com.company.roomservice.service.RoomService;
import com.company.roomservice.viewmodel.RoomViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomViewModel createRoom(@RequestBody @Valid RoomViewModel roomVM){
        return service.saveRoom(roomVM);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomViewModel> getAllRooms(){
        return service.findAllRooms();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomViewModel getRoom(@PathVariable("id") int id){
        RoomViewModel roomVM = service.findRoomById(id);
        if(roomVM == null){
            throw new NotFoundException("Room with the id " + id + " could not be found.");
        }
        return roomVM;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRoom(@RequestBody @Valid RoomViewModel roomVM, @PathVariable("id") int id){
        if(roomVM.getId() == null){
            roomVM.setId(id);
        }
        if(roomVM.getId() != id){
            throw new IllegalArgumentException("Room id on the path must match the id ib on the roomVM");
        }
        service.updateRoom(roomVM);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable("id") int id){
        service.removeRoom(id);
    }
}

















