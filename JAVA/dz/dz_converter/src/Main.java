import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Map<Character, Integer> roman =  new HashMap<Character, Integer>();
    static {
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);
    }

    public static Map<Integer, String> toRomanMap = new LinkedHashMap<>();
    static {
        toRomanMap.put(1000, "M");
        toRomanMap.put(900, "CM");
        toRomanMap.put(500, "D");
        toRomanMap.put(400, "CD");
        toRomanMap.put(100, "C");
        toRomanMap.put(90, "XC");
        toRomanMap.put(50, "L");
        toRomanMap.put(40, "XL");
        toRomanMap.put(10, "X");
        toRomanMap.put(9, "IX");
        toRomanMap.put(5, "V");
        toRomanMap.put(4, "IV");
        toRomanMap.put(1, "I");
    }

    // из римских в арабские
    public static int toArabic(String roman) {
        // проверяем на пустую строку
        if (roman == null || roman.isEmpty()) {
            return -1;
        }

        String s = roman.trim().toUpperCase();
        // Ищем есть ли недопустимый символ
        for (char c : s.toCharArray()){
            if (!Main.roman.containsKey(c)) {
                System.out.println("Недопустимый символ: " + c);
                return -1;
            }
        }

        int number = 0;
        for (int i = 0; i < s.length(); i++) {
            if( i + 1 < s.length() && Main.roman.get(s.charAt(i)) < Main.roman.get(s.charAt(i + 1))) {
                number += (Main.roman.get(s.charAt(i + 1)) -  Main.roman.get(s.charAt(i)));
                i++;
            } else {
                number += Main.roman.get(s.charAt(i));
            }
        }
        return number;
    }
    // из арабских в римские
    public static String toRoman(int number) {
        if (number < 1 || number > 3999) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, String > entry : toRomanMap.entrySet()) {
            int value = entry.getKey();
            String symbol = entry.getValue();
            while (number >= value) {
                sb.append(symbol);
                number -= value;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.matches("\\d+")){
            System.out.println(toRoman(Integer.parseInt(input)));
        } else if (input.toUpperCase().matches("[IVXLCDM]+")) {
            System.out.println(toArabic(input));
        }
    }


}