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
        // Создание узлов из списка ветофоров
        Node[] nodes = new Node[4];
        for (int i = 0; i < 4; i++) {
            nodes[i] = new Node(pedestrianTrafficLights.get(i), pedestrianTrafficLights.get(i + 4), carTrafficLights.get(i));
            nodes[i].stopNode();
        }

        // Запуск алгоритма до внешнего прерывания
        while (!isInterrupted()) {
            // Поиск наиболее нагруженного узла
            Node loadedNode = maxWeightNode(maxWeightNode(nodes[0], nodes[1]), maxWeightNode(nodes[2], nodes[3]));

            // Разгрузка узла
            loadedNode.unloadNode();

            // Некоторая оптимизация, если наиболее нагруженный узел не имеет очереди машин или пешеходов
            if (loadedNode.isPedestrianTraficEmpty()) {
                if (loadedNode.equals(nodes[0])) nodes[2].startCarTrafficLight();
                else if (loadedNode.equals(nodes[1])) nodes[3].startCarTrafficLight();
                else if (loadedNode.equals(nodes[2])) nodes[0].startCarTrafficLight();
                else nodes[1].startCarTrafficLight();
            } else if (loadedNode.isCarTraficEmpty()) {
                if (loadedNode.equals(nodes[0])) nodes[2].startPedestrianTrafficLight();
                else if (loadedNode.equals(nodes[1])) nodes[3].startPedestrianTrafficLight();
                else if (loadedNode.equals(nodes[2])) nodes[0].startPedestrianTrafficLight();
                else nodes[1].startPedestrianTrafficLight();
            }

            // Запуск ожидания для следующего цикла анализа
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
            for (Node node : nodes) {
                node.stopNode();
            }
        }
    }

    // Метод для поиска наиболее нагруженного узла
    private Node maxWeightNode(Node node1, Node node2) {
        int node1Weight = node1.getWeight(pedestrianWC, carsWC, timeWC);
        int node2Weight = node2.getWeight(pedestrianWC, carsWC, timeWC);
        return node1Weight > node2Weight ? node1 : node2;
    }
}
