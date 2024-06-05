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
        Node[] nodes = new Node[4];
        for (int i = 0; i < 4; i++) {
            nodes[i] = new Node(pedestrianTrafficLights.get(i), pedestrianTrafficLights.get(i+4), carTrafficLights.get(i));
            nodes[i].stopNode();
        }
        while (!isInterrupted()) {
            Node loadedNode = maxWeightNode(maxWeightNode(nodes[0], nodes[1]), maxWeightNode(nodes[2], nodes[3]));
            loadedNode.unloadNode();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Задержка перед следующим циклом заершена");
            }
            for (Node node : nodes) {
                node.stopNode();
            }
        }
    }

    private Node maxWeightNode (Node node1, Node node2) {
        int node1Weight = node1.getWeight(pedestrianWC, carsWC, timeWC);
        int node2Weight = node2.getWeight(pedestrianWC, carsWC, timeWC);
        return node1Weight > node2Weight ? node1 : node2;
    }
}
