interface TaskSystem{
    void addTask(String task);
    void connect();
}

class OldTaskSystem implements TaskSystem{

    @Override
    public void addTask(String task) {
        System.out.println("Добавлена задача: " + task);
    }

    @Override
    public void connect() {
        System.out.println("Подключение к старой системе");
    }
}

class NewTaskSystem{

    public void addTask(String task, String priority){
        System.out.println("Добавлена задача " + task + ", приоритет: " + priority);
    }

    public void connection(){
        System.out.println("Подключение к новой системе");
    }
}

class NewTaskAdapter implements TaskSystem{
    private NewTaskSystem newTaskSystem;

    public NewTaskAdapter(NewTaskSystem newTaskSystem){
        this.newTaskSystem = newTaskSystem;
    }


    @Override
    public void addTask(String task) {
        newTaskSystem.addTask(task, "Новый");
    }

    @Override
    public void connect() {
        newTaskSystem.connection();
    }
}

public class Main {
    public static void main(String[] args) {
        TaskSystem oldSystem = new OldTaskSystem();
        oldSystem.addTask("Написать код");
        oldSystem.connect();

        NewTaskSystem newSystem = new NewTaskSystem();
        TaskSystem newAdapter = new NewTaskAdapter(newSystem);
        newAdapter.connect();
        newAdapter.addTask("Дебагинг");
    }
}