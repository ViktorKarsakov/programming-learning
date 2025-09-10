import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ModuleFour.outputNumbers();
        ModuleFour.factorial(scanner);
        ModuleFour.fibonacci(scanner);
        System.out.println("Проверка на простое число");
        if (ModuleFour.isPrime(scanner)){
            System.out.println("Число простое");
        } else {
            System.out.println("Число не простое");
        }
        ModuleFour.stars();

    }
}