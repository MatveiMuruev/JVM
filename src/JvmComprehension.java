public class JvmComprehension {
    public static void main(String[] args) {
        int i = 1; //запись во фрейм "main" в StackMemory
        Object o = new Object();// создание объекта Object в Heap для записи данных об объекте, переменной присваивается ссылка на область памяти в Heap где хранится созданный Object
        Integer ii = 2; // создание объекта Integer в Heap для записи данных об объекте, переменной присваивается ссылка на область памяти в Heap где хранится созданный Integer
        printAll(o, i, ii); //инициализация статического метода, создание фрейма "printAll" в StackMemory и переменной типа int с присовением значения 1, создаются объекты Object, Integer в Heap, переменным присваиваются ссылки на уе созданные объекты
        System.out.println("finished");//поиск и загрузка класса System, инициализация методов, создание объекта String в Heap для записи данных об объекте, переменной присваивается ссылка на область памяти в Heap где хранится созданный String
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;// создание объекта в Heap и запись данных об объекте
        System.out.println(o.toString() + i + ii); //поиск и загрузка класса System, инициализация методов
    }
}
