package ru.ivanov9090.algorithms;

import ru.ivanov9090.trafficlights.CarTrafficLight;
import ru.ivanov9090.trafficlights.PedestrianTrafficLight;
import ru.ivanov9090.trafficlights.TrafficLight;

import java.util.List;

public class Algorithm extends Thread {
    private final int carsWeight;
    private final int pedestrianWeight;
    private final int timeWeight;
    private List<PedestrianTrafficLight> pedestrianTrafficLights;
    private List<CarTrafficLight> carTrafficLights;

    public Algorithm(List<PedestrianTrafficLight> pedestrianTrafficLights,
                     List<CarTrafficLight> carTrafficLights,
                     int carsWeight,
                     int pedestrianWeight,
                     int timeWeight) {
        this.pedestrianTrafficLights = pedestrianTrafficLights;
        this.carTrafficLights = carTrafficLights;
        this.carsWeight = carsWeight;
        this.pedestrianWeight = pedestrianWeight;
        this.timeWeight = timeWeight;
    }

    @Override
    public void run() {
        Node node1 = new Node(pedestrianTrafficLights.get(0), pedestrianTrafficLights.get(1), carTrafficLights.get(0));
        Node node2 = new Node(pedestrianTrafficLights.get(2), pedestrianTrafficLights.get(3), carTrafficLights.get(1));
        Node node3 = new Node(pedestrianTrafficLights.get(4), pedestrianTrafficLights.get(5), carTrafficLights.get(2));
        Node node4 = new Node(pedestrianTrafficLights.get(5), pedestrianTrafficLights.get(6), carTrafficLights.get(3));

    }
}
