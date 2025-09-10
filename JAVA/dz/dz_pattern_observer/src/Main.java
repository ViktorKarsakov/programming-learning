import java.util.ArrayList;
import java.util.List;

interface NewsSubscriber{
    void update(String news);
}

class EmailSubscriber implements NewsSubscriber{
    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String news) {
        System.out.println("На email " + email + " получена новость: " + news);
    }
}

class PhoneSubscriber implements NewsSubscriber{
    private String phone;

    public PhoneSubscriber(String phone) {
        this.phone = phone;
    }

    @Override
    public void update(String news) {
        System.out.println("На номер телефона " + phone + " получена новость: " + news);
    }
}

class NewsAgency{
    private List<NewsSubscriber> subscribers = new ArrayList<>();
    private String latestNews;

    public void addSubscriber (NewsSubscriber subscriber){
        subscribers.add(subscriber);
        System.out.println(subscriber + " подписался на рассылку новостей");
    }

    public void removeSubscriber(NewsSubscriber subscriber){
        subscribers.remove(subscriber);
        System.out.println(subscriber + " отписался от рассылки новостей");
    }

    public void notifySubscribers(){
        for(NewsSubscriber subscriber : subscribers){
            subscriber.update(latestNews);
        }
    }

    public void publishNews(String news){
        this.latestNews = news;
        System.out.println("Публикация новости: " + news);
        notifySubscribers();
    }
}

public class Main {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        EmailSubscriber emailSubscriber = new EmailSubscriber("ghgh@mail.ru");
        PhoneSubscriber phoneSubscriber = new PhoneSubscriber("+7(999) 999 99 99");

        agency.addSubscriber(emailSubscriber);
        agency.addSubscriber(phoneSubscriber);

        agency.publishNews("Скидки в магазине");

        agency.removeSubscriber(phoneSubscriber);

        agency.publishNews("Новая новость");
    }
}