import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class StepTracker {
    HashMap<Integer, MonthData> stepHash;
    private int needStepPerDay;     // переменная целевого количества шагов в день
    private int maxCountMonth;
    private int minCountMonth;
    private int maxCountDay;
    public StepTracker() {      // конструктор для хэш-таблицы (месяцы указаны цифрами для избежания ошибки при выборе пользователем)
        stepHash = new HashMap<>();
        needStepPerDay = 10_000;
        maxCountMonth = 12;     // для удобства добавил максимальное и минимальное количество месяцев, а также количество дней в месяце
        minCountMonth = 1;      // можно в теории доработать и сделать корректировку этих переменных пользователем
        maxCountDay = 30;
        for (int month = minCountMonth; month <= maxCountMonth; month++) {  // заполнение хёш-таблицы
            stepHash.put(month, new MonthData());
        }
    }
    class MonthData {       // отдельный класс с днями месяца
        int[] dayOfMonth;
        public MonthData() {dayOfMonth = new int[maxCountDay];}
    }
    public int getNeedStepPerDay() {return needStepPerDay;}
    public void setNeedStepPerDay(int newValue) {needStepPerDay = newValue;}
    public int getMaxCountMonth() {return maxCountMonth;}
    public int getMinCountMonth() {return minCountMonth;}
    public int getMaxCountDay() {return maxCountDay;}

    public void addStepOfDay (int month, int day, int dayStep) {   // метод добавления данных в хэш-таблицу (входящие: номер месяца, день, количество шагов)
        MonthData intDay = stepHash.get(month);
        intDay.dayOfMonth[day - 1] = dayStep;
        stepHash.put(month, intDay);
    }

    public void printStepsOfMonth (int month) { // метод вывода статистики (входящие: номер месяца)
        MonthData monthPrint = stepHash.get(month);     // отдельный массив для анализа берется из хэш-таблицы
        for (int i = 0; i < monthPrint.dayOfMonth.length; i++) {
            System.out.print((i+1) + " день: " + monthPrint.dayOfMonth[i] + ", "); // вывод количества пройденных шагов (по заданному условиями формату)
        }
        System.out.println();
        System.out.println("Общее количество пройденных шагов: " + getSumSteps(month));
        System.out.println("Максимальное пройденное количство шагов: " + getMaxSteps(month));
        System.out.println("Среднее количество шагов: " + getAverageSteps(month));
        Converter converter = new Converter();      // отдельный класс конвертера, для подсчета киллометров и калорий
        System.out.println("Пройденная дистанция (в км): " + converter.walkDistance(getSumSteps(month)));
        System.out.println("Количество сожжённых килокалорий: " + converter.caloriesBurn(getSumSteps(month)));
        System.out.println("Максимальное количество подряд идущих дней, в течение которых количество шагов за день было выше целевого " + getMaxChainSteps(month));
    }

    public int getSumSteps(int month) {     // метод подсчета общей суммы пройденных шагов
        MonthData monthPrint = stepHash.get(month);
        int sumStepsMonth = 0;
        for (int i = 0; i < monthPrint.dayOfMonth.length; i++) {sumStepsMonth += monthPrint.dayOfMonth[i];}
        return sumStepsMonth;
    }

    public int getMaxSteps(int month) {     // метод определения масимального количества пройденных шагов
        MonthData monthPrint = stepHash.get(month);
        int maxStepsMonth = 0;
        for (int i = 0; i < monthPrint.dayOfMonth.length; i++) {
            if (monthPrint.dayOfMonth[i] > maxStepsMonth) {maxStepsMonth = monthPrint.dayOfMonth[i];}
        }
        return maxStepsMonth;
    }

    public BigDecimal getAverageSteps(int month) {      // метод определения среднего количества шагов (с округлением до 3 знаков после запитой)
        double sumSteps = (double) getSumSteps(month);
        double maxDays = (double) getMaxCountDay();
        double average = sumSteps / maxDays;
        BigDecimal result = new BigDecimal(average);
        result = result.setScale(3, RoundingMode.HALF_UP);
        return result;
    }

    public int getMaxChainSteps(int month) {       // метод определения максимальной цепочки дней, с количеством выше целевого значения
        MonthData monthPrint = stepHash.get(month);
        int maxChain = 0;
        int tempMaxChain = 0;
        for (int i = 0; i < monthPrint.dayOfMonth.length; i++) {
            if (monthPrint.dayOfMonth[i] >= getNeedStepPerDay()) {tempMaxChain++;
            } else {
                if (tempMaxChain > maxChain) {maxChain = tempMaxChain;}
                tempMaxChain = 0;
            }
        }
        return maxChain;
    }

    public void printNumbersOfMonth() {     //метод вывод списка месяцев с порядковыми номерами для удобства пользователя
        for (int i = 1; i < 13; i++) {System.out.print(numberToMonth(i) + " - " + i + ". ");}
        System.out.println();
    }

    String numberToMonth (int month) {  // метод преобразования цифры в название месяца
        switch (month) {
            case (1):
                return "Январь";
            case (2):
                return "Февраль";
            case (3):
                return "Март";
            case (4):
                return "Апрель";
            case (5):
                return "Май";
            case (6):
                return "Июнь";
            case (7):
                return "Июль";
            case (8):
                return "Август";
            case (9):
                return "Сентябрь";
            case (10):
                return "Октябрь";
            case (11):
                return "Ноябрь";
            case (12):
                return "Декабрь";
            default:
                return "Ошибочный номер месяца";    // в теории, согласно проверки ввода данных, это условия никогда не сработает (но оставил на всякий случай)
        }
    }
}

