package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String hash = "5694d08a2e53ffcae0c3103e5ad6f6076abd960eb1f8a56577040bc1028f702b";
        String chars = "cdeo";

        String result = crackSha256(hash, chars);
        System.out.println(result);
    }

    public static String crackSha256(String hash, String chars){
        List<String> permutations = new ArrayList<>();
        permute(chars.toCharArray(), 0, permutations);
        for (String s : permutations){
            if (sha256(s).equals(hash)){
                return s;
            }
        }
        return null;
    }

    public static String sha256(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash){
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void permute(char[] arr, int index, List<String> result){
        if (index == arr.length - 1){
            result.add(new String(arr));
            return;
        }

        for (int i = index; i < arr.length; i++){
            swap(arr, index, i);
            permute(arr, index + 1, result);
            swap(arr, index, i);
        }
    }

    public static void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}