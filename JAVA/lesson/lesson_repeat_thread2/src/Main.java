import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static volatile boolean stopSort = true;
    public static List<Integer> list = new ArrayList<>();
    public static volatile int sortedCount = 0;

    public static void printArray() {
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void bubbleSort() {
        int n = list.size();
        sortedCount = 0;

        for (int i = 0; i < n - 1 && !stopSort; i++) {
            for (int j = 0; j < n - i - 1 && !stopSort; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    synchronized (Main.class) {
                        int temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
            sortedCount = i + 1;
        }
    }

    public static void main(String args[]) {
        // Инициализация массива случайными числами
        for (int i = 0; i < 1000000; i++) {
            list.add((int) (Math.random() * 10000));
        }

        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                if (stopSort) {
                    clearConsole();
                    System.out.println("1. Запустить сортировку");
                    System.out.println("2. Вывести массив");
                } else {
                    clearConsole();
                    System.out.println("Идет сортировка...");
                    System.out.println("1. Приостановить сортировку");
                    System.out.println("Отсортировано элементов: " + sortedCount);
                }

                if (scanner.hasNextInt()) {
                    int command = scanner.nextInt();
                    synchronized (Main.class) {
                        if (command == 1) {
                            stopSort = !stopSort;
                            if (stopSort) {
                                clearConsole();
                                System.out.println("Сортировка приостановлена");
                                System.out.println("Всего отсортировано элементов: " + sortedCount);
                                System.out.println("1. Продолжить сортировку");
                                System.out.println("2. Вывести массив");
                            }
                        } else if (command == 2) {
                            printArray();
                        }
                    }
                }
            }
        });
        inputThread.start();

        Thread sortThread = new Thread(() -> {
            while (true) {
                if (!stopSort) {
                    bubbleSort();
                    if (!stopSort) {
                        stopSort = true;
                        clearConsole();
                        System.out.println("Сортировка завершена!");
                        System.out.println("1. Запустить сортировку (сначала)");
                        System.out.println("2. Вывести массив");
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
            }
        });
        sortThread.start();
    }
}