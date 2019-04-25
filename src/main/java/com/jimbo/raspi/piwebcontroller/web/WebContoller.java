package com.jimbo.raspi.piwebcontroller.web;

import com.jimbo.raspi.piwebcontroller.Response;
import com.jimbo.raspi.piwebcontroller.service.PiWebControllerGpioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebContoller {

    @Autowired
    private PiWebControllerGpioService gpioController;

    @RequestMapping(value = "/setPinState", method = RequestMethod.GET)
    public Response setPinState(@RequestParam(value="gpioId") int gpioId,
                                @RequestParam(value="onOrOff") boolean onOrOff) {

        Response response = gpioController.setPinState(gpioId, onOrOff);

        return response;
    }

}
