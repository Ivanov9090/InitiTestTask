package ru.ivanov9090.algorithms;

/*
Класс для адаптивного алгоритма работы.
Имеет весовые коэффициенты, которые позволяют подстраивать алгоритм под различные условия.
 */

import ru.ivanov9090.trafficlights.CarTrafficLight;
import ru.ivanov9090.trafficlights.PedestrianTrafficLight;

import java.util.List;

public class Algorithm extends Thread {
    private final int carsWC; // Весовой коэффициент для машин
    private final int pedestrianWC; // Весовой коэффициент для пешиходов
    private final int timeWC; // Весовой коэффициент для времени, которое объект ожидал для продолжения дижения
    private List<PedestrianTrafficLight> pedestrianTrafficLights; // Список пешеходных светофоров
    private List<CarTrafficLight> carTrafficLights; // Список светофоров для машин

    public Algorithm(List<PedestrianTrafficLight> pedestrianTrafficLights,
                     List<CarTrafficLight> carTrafficLights,
                     int carsWC,
                     int pedestrianWC,
                     int timeWC) {
        this.pedestrianTrafficLights = pedestrianTrafficLights;
        this.carTrafficLights = carTrafficLights;
        this.carsWC = carsWC;
        this.pedestrianWC = pedestrianWC;
        this.timeWC = timeWC;
    }

    @Override
    public void run() {
        Node node1 = new Node(pedestrianTrafficLights.get(0), pedestrianTrafficLights.get(1), carTrafficLights.get(0));
        Node node2 = new Node(pedestrianTrafficLights.get(2), pedestrianTrafficLights.get(3), carTrafficLights.get(1));
        Node node3 = new Node(pedestrianTrafficLights.get(4), pedestrianTrafficLights.get(5), carTrafficLights.get(2));
        Node node4 = new Node(pedestrianTrafficLights.get(5), pedestrianTrafficLights.get(6), carTrafficLights.get(3));
        while (!isInterrupted()) {
            Node loadedNode = maxWeightNode(maxWeightNode(node1, node2), maxWeightNode(node3, node4));
            
        }
    }

    private Node maxWeightNode (Node node1, Node node2) {
        int node1Weight = node1.getWeight(pedestrianWC, carsWC, timeWC);
        int node2Weight = node2.getWeight(pedestrianWC, carsWC, timeWC);
        return node1Weight > node2Weight ? node1 : node2;
    }
}
