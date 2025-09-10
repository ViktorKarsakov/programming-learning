import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> sentences = new ArrayList<>();
        sentences.add("Разбить предложения на слова");
        sentences.add("Подсчитать частоту слов в своей части");
        sentences.add("Объединить результаты из всех потоков в общий словарь");
        int threadCount = 2;

        WordAnalyzer wordAnalyzer = new WordAnalyzer(sentences, threadCount);
        wordAnalyzer.analyze();
        wordAnalyzer.printParts();
    }
}