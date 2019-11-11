package com.company.hoteledgeservice.util.feign;

import com.company.hoteledgeservice.viewmodel.RoomViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "room-service")
public interface RoomClient {

    @RequestMapping(value = "/room", method = RequestMethod.GET)
    List<RoomViewModel> getAllRooms();

    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
    RoomViewModel getRoom(@PathVariable("id") int id);

    @RequestMapping(value = "/room/{id}", method = RequestMethod.PUT)
    void updateRoom(@RequestBody @Valid RoomViewModel roomVM, @PathVariable("id") int id);
}
