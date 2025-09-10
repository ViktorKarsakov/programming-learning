import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(float temperature);
}

class WeatherStation {
    private float temperature;
    private List<Observer> observers = new ArrayList<>();

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

// Устройство отображения температуры
class DisplayDevice implements Observer {
    private String name;

    public DisplayDevice(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + ": Текущая температура " + temperature + "°C");
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        DisplayDevice display1 = new DisplayDevice("Дисплей 1");
        DisplayDevice display2 = new DisplayDevice("Дисплей 2");

        // Подписываем наблюдателей
        weatherStation.addObserver(display1);
        weatherStation.addObserver(display2);

        // Изменяем температуру
        weatherStation.setTemperature(25.5f);
        weatherStation.setTemperature(30.0f);

        // Отписываем один из наблюдателей
        weatherStation.removeObserver(display1);

        // Снова изменяем температуру
        weatherStation.setTemperature(22.0f);
    }
}