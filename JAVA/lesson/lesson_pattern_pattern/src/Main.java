abstract class Beverage {
    // Шаблонный метод
    public final void prepare() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // Общие шаги
    private void boilWater() {
        System.out.println("Кипятим воду.");
    }

    private void pourInCup() {
        System.out.println("Наливаем в чашку.");
    }

    // Шаги, которые должны быть реализованы подклассами
    protected abstract void brew();
    protected abstract void addCondiments();
}

// Чай
class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Завариваем чай.");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавляем лимон.");
    }
}

// Кофе
class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Варим кофе.");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавляем сахар и молоко.");
    }
}

public class Main {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        Beverage coffee = new Coffee();

        System.out.println("Готовим чай:");
        tea.prepare();

        System.out.println("\nГотовим кофе:");
        coffee.prepare();
    }
}