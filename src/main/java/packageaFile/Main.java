package packageaFile;

import java.util.Scanner;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            logger.info("Запуск программы конвертера единиц");

            System.out.println("Выберите тип величины:");
            System.out.println("1 - Длина (m, km, cm)");
            System.out.println("2 - Масса (g, kg)");
            System.out.print("Ваш выбор: ");

            int typeChoice = scanner.nextInt();
            scanner.nextLine();

            ConversionType type;
            if (typeChoice == 1) {
                type = ConversionType.LENGTH;
            } else if (typeChoice == 2) {
                type = ConversionType.MASS;
            } else {
                throw new IllegalArgumentException("Неизвестный тип величины");
            }

            logger.info("Пользователь выбрал тип: " + type);

            System.out.print("Введите значение для перевода: ");
            double value = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Единица ИЗ (например, m, km, cm, g, kg): ");
            String fromUnit = scanner.nextLine().trim();

            System.out.print("Единица В (например, m, km, cm, g, kg): ");
            String toUnit = scanner.nextLine().trim();

            Converter converter = new Converter(type, fromUnit, toUnit);
            double result = converter.convert(value);

            System.out.println("Результат: " + value + " " + fromUnit + " = "
                    + result + " " + toUnit);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            logger.error("Неверный ввод пользователя: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
            logger.error("Исключение: ", e);
        } finally {
            logger.info("Программа завершена");
            scanner.close();
        }
    }
}
