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
import ru.ivanov9090.trafficlights.Color;
import ru.ivanov9090.trafficlights.PedestrianTrafficLight;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    public int getWeight(int pedestrianWC, int carsWC, int timeWC) {
        return getPedestrianWeight(pedestrianWC)+getCarWeight(carsWC)+getTimeWeight(timeWC);
    }

    private int getPedestrianWeight (int pedestrianWC) {
        int firstQueueSize = firstPedestrianTrafficLight.getDetectedObjects().size();
        int secondQueueSize = secondPedestrianTrafficLight.getDetectedObjects().size();
        return (firstQueueSize + secondQueueSize)*pedestrianWC;
    }

    private int getCarWeight (int carsWC) {
        int carQueueSize = carTrafficLight.getDetectedObjects().size();
        return carQueueSize*carsWC;
    }

    private int getTimeWeight (int timeWC){
        int timeWeight = 0;
        if (!carTrafficLight.getDetectedObjects().isEmpty()){
            timeWeight += (int) carTrafficLight.getDetectedObjects().get(0).until(LocalDateTime.now(), ChronoUnit.SECONDS);
        }
        if (!firstPedestrianTrafficLight.getDetectedObjects().isEmpty()){
            timeWeight += (int) firstPedestrianTrafficLight.getDetectedObjects().get(0).until(LocalDateTime.now(), ChronoUnit.SECONDS);
        }
        if (!secondPedestrianTrafficLight.getDetectedObjects().isEmpty()){
            timeWeight += (int) secondPedestrianTrafficLight.getDetectedObjects().get(0).until(LocalDateTime.now(), ChronoUnit.SECONDS);
        }
        return timeWeight*timeWC;
    }

    public void unloadNode() {
        carTrafficLight.changeColor(Color.ORANGE);
        firstPedestrianTrafficLight.changeColor(Color.GREEN);
        secondPedestrianTrafficLight.changeColor(Color.GREEN);
        carTrafficLight.changeColor(Color.GREEN);
    }

    public void stopNode() {
        carTrafficLight.changeColor(Color.ORANGE);
        firstPedestrianTrafficLight.changeColor(Color.RED);
        secondPedestrianTrafficLight.changeColor(Color.RED);
        carTrafficLight.changeColor(Color.RED);
    }
}
