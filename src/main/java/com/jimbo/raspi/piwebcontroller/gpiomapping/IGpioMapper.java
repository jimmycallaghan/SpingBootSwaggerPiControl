package com.jimbo.raspi.piwebcontroller.gpiomapping;

import com.pi4j.io.gpio.Pin;

import java.util.Map;

public interface IGpioMapper {

    Map<Integer, Pin> getMap();

}
