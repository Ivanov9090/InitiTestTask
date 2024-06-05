package ru.ivanov9090.trafficlights;

/*
Класс описывает светофор для пешеходов.
В класс при создании передается уникальный идентификатор и лист, в который реальный светофор помещает (или удаляет)
время появления пешеходов.
 */

import java.time.LocalDateTime;
import java.util.List;

public class PedestrianTrafficLight extends TrafficLight {
    public PedestrianTrafficLight(int id, List<LocalDateTime> list) {
        super(id);
        this.detectedObjects = list;
    }

    @Override
    public void changeColor(Color color) {
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
