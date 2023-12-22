package io.skrat.TaskOne;

//MARK: Singleton
class Connection {
    // Создаём статическое поле - instance (экземпляр)
    private static Connection instance;

    // Реализуем функцию для создания нового одиночного экземпляра
    public static Connection getInstance() {
        // Проверяем существует ли у нас уже экземпляр или нет
        if (null != instance)
            return instance;

        // Если экземпляра не существует, то создаём новый
        instance = new Connection();
        return instance;
    }
}

class SingletonService {
    /*
    exec - функция, которая создаёт новые экземляры,
    для проверки работы паттерна одиночки и выводит адрес экземпляра в консоль
    */
    public void exec() {
        Connection connection1 = Connection.getInstance();
        Connection connection2 = Connection.getInstance();

        System.out.println(connection1.toString());
        System.out.println(connection2.toString());
    }
}

public class Application {
    public static void main(String[] args) {
        // Создаём экземляр класса SingletonService
        SingletonService shared = new SingletonService();
        // Вызывавем метод exec
        shared.exec();
    }
}