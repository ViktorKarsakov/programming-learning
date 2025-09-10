public class CustomThread extends Thread{
    private String input;
    private boolean countVowels;
    private static int vowelsCount = 0;
    private static int consonantsCount = 0;

    public CustomThread(String input, boolean countVowels){
        this.input = input;
        this.countVowels = countVowels;
    }

    @Override
    public void run() {
        String vowels = "аеёиоуыэюя";
        char[] chars = input.toLowerCase().toCharArray();
        for(char ch : chars){
            if(countVowels){
                if(vowels.indexOf(ch) != -1){
                    incrementVowels();
                }
            } else {
                if(vowels.indexOf(ch) == -1){
                    incrementConsonants();
                }
            }

        }
    }

    private static synchronized void incrementVowels(){
        vowelsCount++;
    }

    private static synchronized void incrementConsonants(){
        consonantsCount++;
    }

    public static int getVowelsCount() {
        return vowelsCount;
    }

    public static int getConsonantsCount() {
        return consonantsCount;
    }
}
