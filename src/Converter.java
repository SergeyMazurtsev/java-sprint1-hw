public class Converter {

    double walkDistance(int steps) {    // метод подсчета дистанции в киллометрах
        return (steps * 75.0 / 100000);
    }

    double caloriesBurn (int steps) {   // метод подсчета калорий
        return (steps * 50.0 / 1000);
    }
}
