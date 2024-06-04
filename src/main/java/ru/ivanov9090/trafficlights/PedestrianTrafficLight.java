package ru.ivanov9090.trafficlights;

/*
Класс описывает светофор для пешеходов.
Для списка обноруженных объектов используется ArrayList, однако при необходимости можно использовать LinkedList.
 */

import java.util.ArrayList;

public class PedestrianTrafficLight extends TrafficLight {
    public PedestrianTrafficLight(int id) {
        super(id);
        this.detectedObjects = new ArrayList<>();
    }

    @Override
    protected void changeColor(Color color) {
        switch (color) {         // Исключаем любые состояния для пешеходного светофора, кроме зеленого и красного
            case RED:
                this.color = Color.RED;
                break;
            case GREEN:
                this.color = Color.GREEN;
                break;
        }
    }
}
