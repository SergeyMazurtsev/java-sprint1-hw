/* Постарался максимально переработать код, с учетом замечаний и рекомендаций. Надеюсь не сликом "замудрил".
Иток по порядку:
1. Удалил из пакета все не нужные файлы и папки, остались только src и out.
2. Первичное значение целевого количества шагов убрал в конструктор StepTracker, также установил на 10_000.
3. Изметода main убрал обработку ввода команд пользователя в отдельный класс HandleInputCommands.
4. Проверка вводимых данных пользователя теперь проводится на каждом этапе, для этого создан отдельный класс CheckInput.
    - первично проверяется ввод именно int, а не String;
    - при необходимости проводится проверка на отрицательное значение отдельным методом checkNegativeNum в этом классе;
    - при необходимости проводится проверка ввода значения строго в определенном диапазоне методом checkInputNum в этом классе;
5. Применил в классе StepTracker принципы инкапсуляции (Геттер и Сеттер) ко всем данным в конструкторе. Так же использовал
эти принципы в классе CheckInput.
6. В классе StepTracker изменил принцип расчета информации по статистике. Все расчетые данные вынес в отдельные методы:
getSumSteps, getMaxSteps, getAverageSteps (этот метод дополнительно округляет значение до 3 знаков после запятой),getMaxChainSteps.
7. Доработал код по подсчету максимальной цепочки дней с двумя переменными, в отдельном методе.
8. Доработал расчет среднего количества шагов, вывод в виде типа double.

Итог:
1. На каждом этапе проводится проверка вводимых пользователем данных.
2. Каждый класс имеет свою цель и соответствующие методы в каждом из них (согласно цели):
    Main - основной класс первичного меню.
    StepTracker - класс обработки сохраняемой информации о шагах, добавление значений, вывод статистики, изменение целевого значения.
    Converter - класс конвертации значений шагов в калории и киллометры.
    HandleInputCommands - класс обработки команд основного меню.
    CheckInput - класс сохранения и проверки вводимых пользователем данных.
3. Код стал более лакончиным, методы имеют малое количество кода, данные разбиты на несколько классов.
*/
import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StepTracker stepTracker = new StepTracker();
        Scanner scanner = new Scanner(System.in);
        printMenu();
        CheckInput userInput = CheckInput.getCheckNum(scanner);         // переменная с проверкой вводимых данных (только цифры)
        HandleInputCommands inputCommands = new HandleInputCommands();
        while (userInput.getOutNum() != 0) {
            if (userInput.getCheckingResult()) {
                if (userInput.getOutNum() == 1) {
                    inputCommands.handleStepsInput(scanner, stepTracker);
                } else if (userInput.getOutNum() == 2) {
                    inputCommands.handlePrintStatistics(scanner, stepTracker);
                } else if (userInput.getOutNum() == 3) {
                    inputCommands.changeDayGoal(scanner, stepTracker);
                }
                printMenu();
                userInput = CheckInput.getCheckNum(scanner);
            }
        }
        System.out.println("Программа завершена");
    }

    public static void printMenu() {
        System.out.println("Введите команду:");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выйти из приложения");
    }
}

