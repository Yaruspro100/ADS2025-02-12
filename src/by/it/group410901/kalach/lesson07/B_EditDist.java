package by.it.group410901.kalach.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        // Размеры строк
        int m = one.length();
        int n = two.length();

        // Создаём таблицу для динамического программирования
        int[][] dp = new int[m + 1][n + 1];

        // Заполняем первую строку и первый столбец
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Удаление i символов
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Вставка j символов
        }

        // Заполняем остальную таблицу
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    // Если символы одинаковые, копируем значение из предыдущего состояния
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Иначе берём минимум из трёх операций + 1
                    dp[i][j] = 1 + Math.min(
                            Math.min(dp[i][j - 1], // Вставка
                                    dp[i - 1][j]), // Удаление
                            dp[i - 1][j - 1] // Замена
                    );
                }
            }
        }

        // Ответ — в правом нижнем углу таблицы
        int result = dp[m][n];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_EditDist.class.getResourceAsStream("dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}