import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WordAnalyzer {
    private List<String> sentences;
    private ConcurrentHashMap<String, Integer> wordParts;
    private int threadCount;

    public WordAnalyzer(List<String> sentences, int threadCount){
        this.sentences = sentences;
        this.threadCount = threadCount;
        wordParts = new ConcurrentHashMap<>();
    }

    private List<List<String>> splitSentences(){
        int partSize = sentences.size() / threadCount;
        List<List<String>> part = new ArrayList<>();
        for(int i = 0; i < threadCount; i++){
            int fromIndex = i * partSize;
            int toIndex = Math.min((i + 1) * partSize, sentences.size());
            part.add(sentences.subList(fromIndex, toIndex));
        }
        return part;
    }

    public void analyze() {
        List<List<String>> parts = splitSentences();
        Thread[] threads = new Thread[threadCount];
        for(int i = 0; i < threadCount; i++){
            WordCounterTask task = new WordCounterTask(parts.get(i));
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for(Thread thread : threads){
            try{
                thread.join();
            } catch (InterruptedException e){
                e.getMessage();
            }
        }
    }

    public Map<String, Integer> getSortedParts(){
        TreeMap<String, Integer> sortedParts = new TreeMap<>();
        sortedParts.putAll(wordParts);
        return sortedParts;
    }

    public void printParts(){
        Map<String, Integer> sorted = getSortedParts();
        for(Map.Entry<String, Integer> entry : sorted.entrySet()){
            System.out.println("Слово: " + entry.getKey() + ", вхождений: " + entry.getValue());
        }
    }

    private class WordCounterTask implements Runnable{
        private List<String> parts;

        public WordCounterTask(List<String> parts){
            this.parts = parts;
        }

        @Override
        public void run() {
            HashMap<String, Integer> localPart = new HashMap<>();
            for(String sentences : parts) {
                String[] words = sentences.split("\\s+");
                for (String word : words) {
                    localPart.put(word, localPart.getOrDefault(word, 0) + 1);
                }
            }
            for(Map.Entry<String, Integer> entry : localPart.entrySet()){
                wordParts.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }


    }
}
