package ru.ivanov9090.algorithms;

/*
Класс описывает узелы, которые анализирует алгоритм.
Т.к. движение машин возожно прямо и направо, то безопасным для пешиходов является переход слева от машины. Таким образом
узел состоит из светофора для машин и двух светофоров для пешиходов, располагающихся слева от машин, к которым относится
светофор.
Узел имеет вес, который расчитывается из длин очередей светофоров, времени обнаружения объектов в очереди и весовых
коэффициентов.
При вычислении весов так же устанавливается флаг наличия машин/пешиходов, необходимый для некоторой оптимизации в
алгоритме.
 */

import ru.ivanov9090.trafficlights.CarTrafficLight;
import ru.ivanov9090.trafficlights.Color;
import ru.ivanov9090.trafficlights.PedestrianTrafficLight;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Node {

    private boolean isPedestrianTraficEmpty;
    private boolean isCarTraficEmpty;
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

    // Расчёт веса всего узла
    public int getWeight(int pedestrianWC, int carsWC, int timeWC) {
        return getPedestrianWeight(pedestrianWC) + getCarWeight(carsWC) + getTimeWeight(timeWC);
    }

    // Расчёт веса пешеходов
    private int getPedestrianWeight(int pedestrianWC) {
        int firstQueueSize = firstPedestrianTrafficLight.getDetectedObjects().size();
        int secondQueueSize = secondPedestrianTrafficLight.getDetectedObjects().size();
        if (firstQueueSize == 0 && secondQueueSize == 0) isPedestrianTraficEmpty = true;
        else isPedestrianTraficEmpty = false;
        return (firstQueueSize + secondQueueSize) * pedestrianWC;
    }

    // Расчёт веса машин
    private int getCarWeight(int carsWC) {
        int carQueueSize = carTrafficLight.getDetectedObjects().size();
        if (carQueueSize == 0) isCarTraficEmpty = true;
        else isCarTraficEmpty = false;
        return carQueueSize * carsWC;
    }

    // Расчёт веса времени
    private int getTimeWeight(int timeWC) {
        int timeWeight = 0;
        if (!carTrafficLight.getDetectedObjects().isEmpty()) {
            timeWeight += (int) carTrafficLight.getDetectedObjects().get(0).until(LocalDateTime.now(), ChronoUnit.SECONDS);
        }
        if (!firstPedestrianTrafficLight.getDetectedObjects().isEmpty()) {
            timeWeight += (int) firstPedestrianTrafficLight.getDetectedObjects().get(0).until(LocalDateTime.now(), ChronoUnit.SECONDS);
        }
        if (!secondPedestrianTrafficLight.getDetectedObjects().isEmpty()) {
            timeWeight += (int) secondPedestrianTrafficLight.getDetectedObjects().get(0).until(LocalDateTime.now(), ChronoUnit.SECONDS);
        }
        return timeWeight * timeWC;
    }

    // Разгрузка узла. Включает зеленый сигнал светофора
    public void unloadNode() {
        if (!isCarTraficEmpty) startCarTrafficLight();
        if (!isPedestrianTraficEmpty) startPedestrianTrafficLight();
    }

    // Включение зеленого сигнала светофора для машин
    public void startCarTrafficLight() {
        carTrafficLight.changeColor(Color.ORANGE);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        carTrafficLight.changeColor(Color.GREEN);
    }

    // Включение зеленого сигнала пешеходного светофора
    public void startPedestrianTrafficLight() {
        firstPedestrianTrafficLight.changeColor(Color.GREEN);
        secondPedestrianTrafficLight.changeColor(Color.GREEN);
    }

    // Включение красного сигнала на всех светофорах узла
    public void stopNode() {
        carTrafficLight.changeColor(Color.ORANGE);
        firstPedestrianTrafficLight.changeColor(Color.RED);
        secondPedestrianTrafficLight.changeColor(Color.RED);
        carTrafficLight.changeColor(Color.RED);
    }

    public boolean isPedestrianTraficEmpty() {
        return isPedestrianTraficEmpty;
    }

    public boolean isCarTraficEmpty() {
        return isCarTraficEmpty;
    }
}
