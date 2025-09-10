import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ModuleFive {

    public static void lengthStr(Scanner scanner){
        System.out.println("Введите строку");
        String str = scanner.nextLine();
        int count = 0;

        for(char c : str.toCharArray()){
            count++;
        }
        System.out.println("Кол-во символов в строке " + str + ": " + count);
    }

    public static void reverseStr(Scanner scanner){
        System.out.println("Введите строку");
        String str = scanner.nextLine();
        String reverse = new StringBuilder(str).reverse().toString();
        System.out.println(reverse);
    }

    public static void vowelCount(Scanner scanner){
        String str = scanner.nextLine().toLowerCase();
        char[] vowels = {'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я'};
        int count = 0;

        for (char c : str.toCharArray()){
            for (char vowel : vowels){
                if(c == vowel){
                    count++;
                }
            }
        }
        System.out.println("в данной строке " + count + " гласных");
    }

    public static void maxMin(){
        Random random = new Random();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++){
            array[i] = random.nextInt(100) + 1;
        }
        System.out.println(Arrays.toString(array));

        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++){
            if (array[i] < min){
                min = array[i];
            }
            if (array[i] > max){
                max = array[i];
            }
        }
        System.out.println("Максимальная: " + max);
        System.out.println("Минимальная: " + min);
    }

    public static void sortBubble(){
        Random random = new Random();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++){
            array[i] = random.nextInt(100) + 1;
        }
        System.out.println(Arrays.toString(array));

        for(int i = 0; i < array.length - 1; i++){
            for (int j = 0; j < array.length - 1 - i; j++){
                if (array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //ModuleFive.lengthStr(scanner);
        //ModuleFive.maxMin();
        ModuleFive.sortBubble();
    }
}