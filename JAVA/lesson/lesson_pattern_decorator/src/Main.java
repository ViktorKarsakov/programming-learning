// Компонент (кнопка)
interface Button {
    void click();
}

// Базовая кнопка
class SimpleButton implements Button {
    @Override
    public void click() {
        System.out.println("Кнопка нажата");
    }
}

// Декоратор для добавления подсветки
class HighlightDecorator implements Button {
    private Button button;

    public HighlightDecorator(Button button) {
        this.button = button;
    }

    @Override
    public void click() {
        System.out.println("Подсветка включена");
        button.click();
        System.out.println("Подсветка выключена");
    }
}

// Декоратор для добавления анимации
class AnimationDecorator implements Button {
    private Button button;

    public AnimationDecorator(Button button) {
        this.button = button;
    }

    @Override
    public void click() {
        System.out.println("Анимация запущена");
        button.click();
        System.out.println("Анимация завершена");
    }
}

public class Main {
    public static void main(String[] args) {
        Button simpleButton = new SimpleButton();
        Button highlightedButton = new HighlightDecorator(simpleButton);
        Button animatedButton = new AnimationDecorator(highlightedButton);

        animatedButton.click();
    }
}