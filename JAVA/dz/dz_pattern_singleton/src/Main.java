public class Main {
    public static void main(String[] args) {
                SettingsManager settings1 = SettingsManager.getInstance();
                SettingsManager settings2 = SettingsManager.getInstance();

                // Проверяем, что это один объект
                System.out.println(settings1 == settings2); // true

                // Меняем настройки
                settings1.setLanguage("Russian");
                settings2.setTheme("Dark");

                // Проверяем, что изменения видны везде
                System.out.println("Язык: " + settings1.getLanguage()); // Russian
                System.out.println("Тема: " + settings2.getTheme());   // Dark
            }
        }
    }
}