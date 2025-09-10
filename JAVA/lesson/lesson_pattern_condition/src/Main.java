interface State {
    void play(Player player);
    void pause(Player player);
    void stop(Player player);
}

// Состояние "Воспроизведение"
class PlayingState implements State {
    @Override
    public void play(Player player) {
        System.out.println("Уже воспроизводится.");
    }

    @Override
    public void pause(Player player) {
        System.out.println("Пауза.");
        player.setState(new PausedState());
    }

    @Override
    public void stop(Player player) {
        System.out.println("Остановлено.");
        player.setState(new StoppedState());
    }
}

// Состояние "Пауза"
class PausedState implements State {
    @Override
    public void play(Player player) {
        System.out.println("Продолжаем воспроизведение.");
        player.setState(new PlayingState());
    }

    @Override
    public void pause(Player player) {
        System.out.println("Уже на паузе.");
    }

    @Override
    public void stop(Player player) {
        System.out.println("Остановлено.");
        player.setState(new StoppedState());
    }
}

// Состояние "Остановлен"
class StoppedState implements State {
    @Override
    public void play(Player player) {
        System.out.println("Начинаем воспроизведение.");
        player.setState(new PlayingState());
    }

    @Override
    public void pause(Player player) {
        System.out.println("Нельзя поставить на паузу, плеер остановлен.");
    }

    @Override
    public void stop(Player player) {
        System.out.println("Уже остановлен.");
    }
}

class Player {
    private State state;

    public Player() {
        this.state = new StoppedState(); // Начальное состояние
    }

    public void setState(State state) {
        this.state = state;
    }

    public void play() {
        state.play(this);
    }

    public void pause() {
        state.pause(this);
    }

    public void stop() {
        state.stop(this);
    }
}

public class Main {
    public static void main(String[] args) {
        Player player = new Player();

        player.play();  // Начинаем воспроизведение
        player.pause(); // Ставим на паузу
        player.play();  // Продолжаем воспроизведение
        player.stop();  // Останавливаем
        player.pause(); // Пытаемся поставить на паузу (не получится)
    }
}