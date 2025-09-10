import java.util.Scanner;

public class ModuleFour {
    public static void outputNumbers(){
        System.out.println("Цикл for: ");
        for (int i = 1; i <= 100; i++){
            System.out.print(i + ", ");
        }
        System.out.println();

        System.out.println("Цикл while: ");
        int n = 0;
        while (n < 100){
            n++;
            System.out.print(n + ", ");
        }
    }

    public static void factorial(Scanner scanner){
        System.out.println("Факториал числа");
        int num = scanner.nextInt();
        long result = 1;
        for (int i = num; i >= 1; i--){
            result *= i;
        }
        System.out.println("Факториал числа " + num + " = " + result);
    }

    public static void fibonacci(Scanner scanner){
        int num = scanner.nextInt();
        int a = 0;
        int b = 1;

        for (int i = 0; i < num; i++){
            System.out.println(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
    }

    public static boolean isPrime(Scanner scanner){
        int n = scanner.nextInt();

        if(n <= 1){
            return false;
        }
        if (n == 2){
            return true;
        }

        for(int i = 2; i < Math.sqrt(n); i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void stars(){
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < i; j++){
                System.out.print('*');
            }
            System.out.println();
        }
    }
}
