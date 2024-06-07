import java.util.Scanner;

interface Operation {
    double calculate(double x, double y);
}

class Addition implements Operation {
    @Override
    public double calculate(double x, double y) {
        return x + y;
    }
}

class Subtraction implements Operation {
    @Override
    public double calculate(double x, double y) {
        return x - y;
    }
}

class Multiplication implements Operation {
    @Override
    public double calculate(double x, double y) {
        return x * y;
    }
}

class Division implements Operation {
    @Override
    public double calculate(double x, double y) {
        if (y == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return x / y;
    }
}

// Фабрика операций
class OperationFactory {
    public static Operation createOperation(String operator) {
        switch (operator) {
            case "+":
                return new Addition();
            case "-":
                return new Subtraction();
            case "*":
                return new Multiplication();
            case "/":
                return new Division();
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}

// Калькулятор
class Calculator {
    private final Scanner scanner;

    public Calculator() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Добро пожаловать в Калькулятор!");
        String choice;
        do {
            try {
                System.out.print("Введите первую цифру: ");
                double x = Double.parseDouble(scanner.nextLine());

                System.out.print("Введите необходимую операцию (+, -, *, /): ");
                String operator = scanner.nextLine();

                System.out.print("Введите второе число: ");
                double y = Double.parseDouble(scanner.nextLine());

                Operation operation = OperationFactory.createOperation(operator);
                double result = operation.calculate(x, y);
                System.out.println("Результат: " + result);
            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА!!! Неверный ввод. Пожалуйста, введите действительный номер.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            System.out.print("Хотите ли вы продолжить? (yes/no): ");
            choice = scanner.nextLine();
        } while (choice.equalsIgnoreCase("yes"));
        scanner.close();
        System.out.println("ДО СВИДАНИЯ!");
    }
}

// Точка входа в программу
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }
}