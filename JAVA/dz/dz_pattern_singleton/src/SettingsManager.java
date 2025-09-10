public class SettingsManager {
    // Единственный экземпляр
    private static SettingsManager instance;

    // Пример настроек
    private String language = "English";
    private String theme = "Light";

    // Приватный конструктор
    private SettingsManager() {
        System.out.println("Менеджер настроек создан!");
    }

    // Метод для получения экземпляра
    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    // Геттеры и сеттеры для настроек
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        System.out.println("Язык изменён на: " + language);
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
        System.out.println("Тема изменена на: " + theme);
    }
}
