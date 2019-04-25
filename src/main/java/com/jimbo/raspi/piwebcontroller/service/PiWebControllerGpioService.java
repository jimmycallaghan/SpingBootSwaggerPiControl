package com.jimbo.raspi.piwebcontroller.service;

import com.jimbo.raspi.piwebcontroller.Response;
import com.jimbo.raspi.piwebcontroller.gpiomapping.GpioMapperPi2VB;
import com.jimbo.raspi.piwebcontroller.gpiomapping.IGpioMapper;
import com.pi4j.io.gpio.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PiWebControllerGpioService {

    private static final Logger logger = LogManager.getLogger(PiWebControllerGpioService.class.getName());

    private GpioController gpio;
    private Map<String, GpioPinDigitalOutput> pinMap = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        logger.info("Creating an instance of the GpioController (pi4j");
        gpio = GpioFactory.getInstance();
    }

    public Response setPinState(int physicalGpioId, boolean onOrOff) {

        logger.info("Request to change state of actual pin " + physicalGpioId + " to " + onOrOff);

        Response response;
        IGpioMapper mapper = new GpioMapperPi2VB();
        Pin pi4jGpioPin = mapper.getMap().get(physicalGpioId);

        logger.info("Actual pin " + physicalGpioId + " maps to pi4j pin id " + pi4jGpioPin.getName());

        if (pi4jGpioPin == null) {
            logger.warn("User has requested to access GPIO pin " + physicalGpioId + " but this has not been mapped in " + mapper.getClass().getName());
            return new Response("Actual pin " + physicalGpioId + " not mapped in IGpioMapper.");
        }

        try {

            // You'll get an exception if you try to initialize the same pin twice. Once initialized, put
            // in a map for later use.
            GpioPinDigitalOutput pin = pinMap.get(pi4jGpioPin.getName());
            if (pin == null) {
                pin = gpio.provisionDigitalOutputPin(pi4jGpioPin, pi4jGpioPin.getName(), PinState.LOW);
                pinMap.put(pi4jGpioPin.getName(), pin);
            }

            logger.info("Setting pin " + pi4jGpioPin.getName() + " to " + (onOrOff == Boolean.FALSE ? "LOW" : "HIGH"));
            pin.setState(onOrOff);

            response = new Response(pi4jGpioPin.getName(), onOrOff == Boolean.FALSE ? "LOW" : "HIGH");

        } catch (Exception e) {
            logger.error("Something went wrong", e);
            response = new Response(e.getMessage());
        }

        return response;
    }

}
