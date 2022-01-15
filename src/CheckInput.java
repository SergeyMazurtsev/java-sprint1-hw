import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckInput {       // отдельный класс для проверки вводимых пользователем цифр
        private int outNum;
        private boolean checkingResult;
        public CheckInput() {
            this.outNum = 0;
            this.checkingResult = true;
        }
        public int getOutNum() {return outNum;}
        public boolean getCheckingResult() {return checkingResult;}
        public void setOutNum(int newNum) {outNum = newNum;}
        public void setCheckResult(boolean newCheck) {checkingResult = newCheck;}

    public static CheckInput getCheckNum(Scanner scanner) {  // метод ввода от пользователя и проверки на тип int
        CheckInput result = new CheckInput();
        try {
            result.setOutNum(scanner.nextInt());
            result.setCheckResult(true);
        } catch (InputMismatchException mne) {
            result.setCheckResult(false);
            result.setOutNum(0);
            System.out.println("Введите число!");
            scanner.nextLine();
        }
        return result;
    }

    boolean checkInputNum(int inputNum, int minNum, int maxNum, String errorMessage) {  // метод проверки ввода цифры в рамках строго диапазона
        boolean result;
        if ((inputNum >= minNum) && (inputNum <= maxNum)) {result = true;
        } else {
            System.out.println(errorMessage);
            result = false;
        }
        return result;
    }

    boolean checkNegativeNum(int inputNum, String errorMessage) { // метод проверки на отрицательное значение введенных данных
        boolean result;
        if (inputNum >= 0) {result = true;
        } else {
            System.out.println(errorMessage);
            result = false;
        }
        return result;
    }
}
