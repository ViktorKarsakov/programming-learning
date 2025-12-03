import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int count = 0;
        int pieces = 1;

        while (pieces < a){
            pieces *= 2;
            count++;
        }
        System.out.println(count);

    }
}