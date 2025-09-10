import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Observer {
    void update(String message);
}

//Потоки, придумать интеграцию с коллекиями, HashMap в Stream

class Base {
    private float temperature;
    private Map<String, List<Observer>> token = new HashMap<>();

    public void setPrice(String tokenName) {
        notifyObservers(tokenName, "Цена была изменена");
    }

    public void addToken(String tokenName){
        token.putIfAbsent(tokenName, new ArrayList<Observer>());
    }

    public void addObserver(Observer observer, String tokenName) {
        token.get(tokenName).add(observer);
    }

    public void removeObserver(Observer observer, String tokenName) {
        token.get(tokenName).remove(observer);
    }

    private void notifyObservers(String tokenName, String message) {
        token.get(tokenName).forEach(tok -> tok.update(message));
    }
}

// Устройство отображения температуры
class Person implements Observer {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Уведомление пришло ему: " + this.name + " " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        Base base = new Base();

        Person display1 = new Person("Максим");
        Person display2 = new Person("Петр");

        Person display3 = new Person("Надя");
        Person display4 = new Person("Саня");

        base.addToken("BTC");

        base.addObserver(display1, "BTC");
        base.addObserver(display2, "BTC");

        base.addToken("Ethirium");

        base.addObserver(display3, "Ethirium");
        base.addObserver(display4, "Ethirium");

        base.setPrice("BTC");

        base.removeObserver(display1, "BTC");
        base.removeObserver(display2, "BTC");

        base.setPrice("BTC");
        base.setPrice("Ethirium");

    }
}