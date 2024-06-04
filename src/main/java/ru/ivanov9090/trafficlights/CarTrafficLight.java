package ru.ivanov9090.trafficlights;
/*
Класс описывает светофор для машин.
Для списка обноруженных объектов используется ArrayList, однако при необходимости можно использовать LinkedList.
 */

import java.util.ArrayList;

public class CarTrafficLight extends TrafficLight {

    public CarTrafficLight(int id) {
        super(id);
        this.detectedObjects = new ArrayList<>();
    }

    @Override
    protected void changeColor(Color color) {
        this.color = color;
    }
}
