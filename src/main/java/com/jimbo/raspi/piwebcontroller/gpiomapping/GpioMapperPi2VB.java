package com.jimbo.raspi.piwebcontroller.gpiomapping;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

import java.util.HashMap;
import java.util.Map;

public class GpioMapperPi2VB implements IGpioMapper {

    @Override
    public Map<Integer, Pin> getMap() {
        Map<Integer, Pin> map = new HashMap<>();
        map.put(17, RaspiPin.GPIO_00);
        map.put(18, RaspiPin.GPIO_01);

        return map;
    }

}
