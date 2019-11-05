package com.company.hoteledgeservice.util.feign;

import com.company.hoteledgeservice.viewmodel.RoomViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "room-service")
@RequestMapping("/room")
public interface RoomClient {

    @GetMapping
    List<RoomViewModel> getAllRooms();

    @GetMapping("/{id}")
    RoomViewModel getRoom(@PathVariable("id") int id);

    @PutMapping("/{id}")
    void updateRoom(@RequestBody @Valid RoomViewModel roomVM, @PathVariable("id") int id);
}
