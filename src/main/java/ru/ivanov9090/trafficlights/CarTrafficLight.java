package ru.ivanov9090.trafficlights;
/*
Класс описывает светофор для машин.
В класс при создании передается уникальный идентификатор и лист, в который реальный светофор помещает (или удаляет)
время появления машин.
 */

import java.time.LocalDateTime;
import java.util.List;

public class CarTrafficLight extends TrafficLight {

    public CarTrafficLight(int id, List<LocalDateTime> list) {
        super(id);
        this.detectedObjects = list;
    }

    @Override
    public void changeColor(Color color) {
        this.color = color;
    }
}
