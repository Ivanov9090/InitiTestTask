package ru.ivanov9090.trafficlights;

/*
Класс описывает абстрактный светофор.
ID - уникальный идентификатор светофора.
Светофор имеет состояние "color", интерпретируемое в зависимости от типа светофора. Набор возможных состояний фиксирован,
но может быть при необходимости расширен (изменением enum Color) или уменьшен (запрет через логику работы сеттеров).
Светофор имеет список обнаруженных объектов. В качестве записей используется время их появления. Порядок соответствует
порядку помещения в список. Длина списка равна длине очереди.
 */

import java.time.LocalDateTime;
import java.util.List;

public abstract class TrafficLight {
    private int id;
    protected Color color;
    protected List<LocalDateTime> detectedObjects;

    protected TrafficLight(int id) {
        this.id = id;
        this.color = Color.RED; // Для избежания аварийных ситуаций, инициализация происходит запрезающим состоянием
    }

    protected abstract void changeColor (Color color);

    public int getId() {
        return id;
    }

    protected List<LocalDateTime> getDetectedObjects() {
        return detectedObjects;
    }

}
