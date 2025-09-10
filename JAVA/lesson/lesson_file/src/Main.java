import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileName = "test.txt";

        // 1. Запись в файл
        writeToFile(fileName, "Привет, это тестовая запись в файл!\nВторая строка.\n");

        // 2. Чтение из файла
        String content = readFromFile(fileName);
        System.out.println("Содержимое файла:\n" + content);

        // 3. Добавление текста в конец файла
        appendToFile(fileName, "\nДобавленная строка в конец файла.");

        // 4. Чтение снова после добавления
        System.out.println("\nОбновленное содержимое:");
        System.out.println(readFromFile(fileName));

        // 5. Получение информации о файле
        getFileInfo(fileName);

        // 6. Копирование файла
        copyFile(fileName, "example_copy.txt");

        // 7. Удаление копии
        deleteFile("example_copy.txt");
    }

    // Метод для записи в файл (перезаписывает содержимое)
    public static void writeToFile(String fileName, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(text);
            System.out.println("Текст успешно записан в файл " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    // Метод для чтения из файла
    public static String readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return content.toString();
    }

    // Метод для добавления текста в конец файла
    public static void appendToFile(String fileName, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(text);
            System.out.println("Текст успешно добавлен в файл " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при добавлении в файл: " + e.getMessage());
        }
    }

    // Метод для получения информации о файле
    public static void getFileInfo(String fileName) {
        Path path = Paths.get(fileName);
        try {
            System.out.println("\nИнформация о файле:");
            System.out.println("Имя файла: " + path.getFileName());
            System.out.println("Путь: " + path.toAbsolutePath());
            System.out.println("Размер: " + Files.size(path) + " байт");
            System.out.println("Дата последнего изменения: " + Files.getLastModifiedTime(path));
            System.out.println("Доступен для чтения: " + Files.isReadable(path));
            System.out.println("Доступен для записи: " + Files.isWritable(path));
        } catch (IOException e) {
            System.err.println("Ошибка при получении информации о файле: " + e.getMessage());
        }
    }

    // Метод для копирования файла
    public static void copyFile(String source, String destination) {
        try {
            Files.copy(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("\nФайл успешно скопирован в " + destination);
        } catch (IOException e) {
            System.err.println("Ошибка при копировании файла: " + e.getMessage());
        }
    }

    // Метод для удаления файла
    public static void deleteFile(String fileName) {
        try {
            Files.deleteIfExists(Paths.get(fileName));
            System.out.println("\nФайл " + fileName + " успешно удален");
        } catch (IOException e) {
            System.err.println("Ошибка при удалении файла: " + e.getMessage());
        }
    }
}