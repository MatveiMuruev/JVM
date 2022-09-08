# "Понимание JVM"
## код для исследования:
```java

public class JvmComprehension {

    public static void main(String[] args) {
        int i = 1;                      // 1
        Object o = new Object();        // 2
        Integer ii = 2;                 // 3
        printAll(o, i, ii);             // 4
        System.out.println("finished"); // 7
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5
        System.out.println(o.toString() + i + ii);  // 6
    }
}
```
##шаг 1: инициализация класса **JVMComprehension**:
```mermaid
graph TB
A[public class JVMComprehension]-->B{Application ClassLoader}
B-->C{Platform ClassLoader}
C-->D{Bootstrap ClassLoader}
D--No-->C
C--No-->B
B--Yes-->E[(загрузка в Metaspace: имя класса, методы класса, поля класса)]
```
##шаг 2: инициализация метода **main**
создание в *Stack Memory* фрейма для записи метода **main**
```mermaid
graph LR
A([public void Main])-- создание фрейм main-->B{Stack Memory <br>фрейм Main <br>примитивная i <br>ссылочная o <br>ссылочная ii}
C[int i = 1]--создание переменной i и запись значения -->B
D[Object o]--создание переменной о -->B
D--создание ссылки на new Object о и запись значения переменной о-->F[(heap <br>Object o, <br>Integer ii)]
F--запись ссылки в значение переменной о-->B
F--запись ссылки в значение переменной ii-->B
G[Integer ii = 2]--создание переменной ii -->B
G--создание ссылки на new Integer ii и запись значения ii-->F
```
##шаг 3: инициализация метода **printAll**
создание в *Stack Memory* фрейма для записи метода **printAll**
```mermaid
graph LR
A([private static void printAll])-- создание фрейм printAll-->B{Stack Memory <br>фрейм printAll <br>примитивная i <br>ссылочная o <br>ссылочная ii <br>ссылочная uselessVar}
C[int i]--создание переменной i и запись значения -->B
F[Object o]--создание переменной о -->B
G[Integer ii = 2]--создание переменной ii -->B
D[(heap <br>Object o, <br>Integer ii, <br>Integer uselessVar)]--запись ссылки в значение переменной о-->B
D--запись ссылки в значение переменной ii-->B
E--создание переменной uselessVar-->D
E[Integer uselessVar = 700]--создание ссылки на new Integer ii и запись значения ii-->B
D--запись ссылки в значение переменной uselessVar-->B
```
##шаг 4: иницализация метода **System.out.println(o.toString() + i + ii)**
```mermaid
graph LR
A([public void println])-- создание фрейм println-->B{Stack Memory <br>фрейм println <br>примитивная i <br>ссылочная o <br>ссылочная ii}
C[int i]--создание переменной i и запись значения -->B
F[Object o]--создание переменной о -->B
G[Integer ii = 2]--создание переменной ii -->B
D[(heap <br> Object o, <br>Integer ii, <br>Integer uselessVar)]--запись ссылки в значение переменной о-->B
D--запись ссылки в значение переменной ii-->B
```
##шаг 5: иницализация метода **toString()**
```mermaid
graph LR
A([public String toString])-- создание фрейм toString-->B{Stack Memory <br>фрейм toString <br>ссылочная o}
F[Object o]--создание переменной о -->B
D[(heap <br>Object o, <br>Integer ii, <br>Integer uselessVar)]--запись ссылки в значение переменной о-->B
```
##шаг 6: исполнение метода **toString()**
после исполнения метода фрейм **toString** удаляется из **Stack Memory**
##шаг 7: исполнение метода **System.out.println(o.toString() + i + ii)**
после исполнения метода фрейм **println** удаляется из **Stack Memory**
##шаг 8: исполнение метода **printAll**
после исполнения метода фрейм **printAll** удаляется из **Stack Memory**
##шаг 9: иницализация метода **System.out.println("finished")**
```mermaid
graph LR
A([public void println])-- создание фрейм println-->B{Stack Memory фрейм println <br> символы строки finished преобразуются в byte <br> и выводятся на экран}
```
##шаг 10: исполнение метода **System.out.println("finished")**
после исполнения метода фрейм **println** удаляется из **Stack Memory**
##шаг 11: завершение метода **main**
после завершения метода фрейм **main** удаляется из **Stack Memory**
все данные из heap и Metaspace удаляются ОС