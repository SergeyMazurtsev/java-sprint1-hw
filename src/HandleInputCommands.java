import java.util.Scanner;

public class HandleInputCommands {

    public void handleStepsInput(Scanner scanner, StepTracker stepTracker) {    // метод сохранения количеста шагов
        System.out.println("Введите номер месяца:");
        stepTracker.printNumbersOfMonth();      //вывод списка месяцев с порядковыми номерами (для удобства пользователя)
        CheckInput stepMonth = CheckInput.getCheckNum(scanner);     // проверка ввода цифр
        if (!stepMonth.getCheckingResult()) {return;}
        if (!stepMonth.checkInputNum(stepMonth.getOutNum(), stepTracker.getMinCountMonth(), stepTracker.getMaxCountMonth(), "Введен некорректный месяц!")) {
            return;
        }
        System.out.println("Введите день:");
        CheckInput stepDay = CheckInput.getCheckNum(scanner);       // проверка ввода цифр
        if (!stepDay.getCheckingResult()) {return;}
        if (!stepDay.checkInputNum(stepDay.getOutNum(), 1, stepTracker.getMaxCountDay(), "Введен некорректный день!")) {
            return;
        }
        System.out.println("Введите количество шагов:");
        CheckInput stepPerDay = CheckInput.getCheckNum(scanner);    // проверка ввода цифр
        if (!stepPerDay.getCheckingResult()) {return;}
        if (!stepPerDay.checkNegativeNum(stepPerDay.getOutNum(), "Введено отрицательно значение количества шагов. Возврат в меню.")) {               //проверка на отрицательное знаение
            stepTracker.addStepOfDay(stepMonth.getOutNum(), stepDay.getOutNum(), stepPerDay.getOutNum());   // добавление количества шагов в указанный месяц
        }
    }

    public void handlePrintStatistics(Scanner scanner, StepTracker stepTracker) {   // метод выведения статистики за месяц
        System.out.println("За какой месяц необходима статистика:");
        stepTracker.printNumbersOfMonth();      //вывод списка месяцев с порядковыми номерами (для удобства пользователя)
        CheckInput month = CheckInput.getCheckNum(scanner);     // проверка ввода цифр
        if (!month.checkInputNum(month.getOutNum(), stepTracker.getMinCountMonth(), stepTracker.getMaxCountMonth(), "Введен некорректный месяц!")) {
            return;
        }
        stepTracker.printStepsOfMonth(month.getOutNum());   // вывод в консоль статистики по определенному месяцу
    }

    public void changeDayGoal(Scanner scanner, StepTracker stepTracker) {   // метод изменения целевого количества шагов в день
        System.out.println("Текущее значение целевого количетсва шагов: " + stepTracker.getNeedStepPerDay() + ".Введите новое знание:");
        CheckInput stepPerDay = CheckInput.getCheckNum(scanner);
        if (!stepPerDay.checkNegativeNum(stepPerDay.getOutNum(), "Ведено отрицательное число, попробуйте снова.")) {               // проверка на отрицательное значение
            stepTracker.setNeedStepPerDay(stepPerDay.getOutNum());        // замена пользователем значения целевого количества шагов в день
        }
    }
}
