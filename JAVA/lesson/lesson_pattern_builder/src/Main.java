import java.util.HashMap;
import java.util.Map;

class HttpRequest {
    private String url;
    private String method; // GET, POST, PUT, DELETE и т.д.
    private Map<String, String> headers; // Заголовки запроса
    private String body; // Тело запроса (например, JSON)

    // Приватный конструктор, чтобы объект создавался только через Builder
    private HttpRequest() {}

    // Геттеры
    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }

    // Внутренний класс Builder
    public static class Builder {
        private HttpRequest request;

        public Builder() {
            request = new HttpRequest();
            request.headers = new HashMap<>(); // Инициализация заголовков
        }

        // Устанавливаем URL
        public Builder setUrl(String url) {
            request.url = url;
            return this;
        }

        // Устанавливаем метод (GET, POST и т.д.)
        public Builder setMethod(String method) {
            request.method = method;
            return this;
        }

        // Добавляем заголовок
        public Builder addHeader(String key, String value) {
            request.headers.put(key, value);
            return this;
        }

        // Устанавливаем тело запроса
        public Builder setBody(String body) {
            request.body = body;
            return this;
        }

        // Собираем объект HttpRequest
        public HttpRequest build() {
            // Проверяем, что обязательные поля заполнены
            if (request.url == null || request.method == null) {
                throw new IllegalStateException("URL и метод обязательны для HTTP-запроса");
            }
            return request;
        }
    }
}

// Использование Builder для создания HTTP-запроса
public class Main {
    public static void main(String[] args) {
        // Создаем POST-запрос с телом и заголовками
        HttpRequest request = new HttpRequest.Builder()
                .setUrl("https://api.example.com/data")
                .setMethod("POST")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer token123")
                .setBody("{\"name\": \"John\", \"age\": 30}")
                .build();

        // Выводим результат
        System.out.println(request);
    }
}