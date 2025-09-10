public class Main {
    public static void main(String[] args) {
        HTML page = new HTML.Builder()
                .setHead(new Head("Страница"))
                .setBody(new Body("<h1>Здравствуйте</n>"))
                .setCss(new Css("styles.css"))
                .setJs(new JS("script.js"))
                .build();

        System.out.println(page);
    }
}