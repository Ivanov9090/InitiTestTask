package ru.ivanov9090.algorithms;

/*
Класс описывает узелы, которые анализирует алгоритм.
Т.к. движение машин возожно прямо и направо, то безопасным для пешиходов является переход слева от машины. Таким образом
узел состоит из светофора для машин и двух светофоров для пешиходов, располагающихся слева от машин, к которым относится
светофор.

Узел имеет вес, который расчитывается из длин очередей светофоров, времени обнаружения объектов в очереди и весовых
коэффициентов.
 */

import ru.ivanov9090.trafficlights.CarTrafficLight;
import ru.ivanov9090.trafficlights.PedestrianTrafficLight;

public class Node {

    private PedestrianTrafficLight firstPedestrianTrafficLight;
    private PedestrianTrafficLight secondPedestrianTrafficLight;
    private CarTrafficLight carTrafficLight;

    public Node(PedestrianTrafficLight firstPedestrianTrafficLight,
                PedestrianTrafficLight secondPedestrianTrafficLight,
                CarTrafficLight carTrafficLight) {
        this.firstPedestrianTrafficLight = firstPedestrianTrafficLight;
        this.secondPedestrianTrafficLight = secondPedestrianTrafficLight;
        this.carTrafficLight = carTrafficLight;
    }



}
