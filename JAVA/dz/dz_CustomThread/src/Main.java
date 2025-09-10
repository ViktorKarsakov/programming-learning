import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String input = scanner.nextLine();

        CustomThread thread1 = new CustomThread(input, true);
        CustomThread thread2 = new CustomThread(input, false);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("В строке : " + input + ": ");
        System.out.println("Гласных: " + CustomThread.getVowelsCount());
        System.out.println("Согласных: " + CustomThread.getConsonantsCount());
    }
}