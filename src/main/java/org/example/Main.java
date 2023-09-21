package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Создаём экземпляры классов Random и PhoneBook
        PhoneBook book = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        // Debug для PhoneBook
        book.setDebug(false);

        // Добавляем контакт в ручную
        book.addContact("Ivan", 8_777_123_12_12L);
        book.addContact("Ivan", 8_777_123_12_12L);

        // Генерируем список контактов
        int contactCounts = numbersScanner("Input contact count for auto-generate: ",
                "Number required!");

        Names[] namesArray = Names.values();
        for (int i = 0; i < contactCounts; i++) {
            book.addContact(String.valueOf(namesArray[new Random().nextInt(namesArray.length)]),
                    generateRandomPhoneNumber(8,707));
        }

        System.out.println("Contact list with " + contactCounts + " users has been created!");
        boolean work = true;
        while (work) {
            System.out.println("""

                    \u001B[0mActions:
                    1. Get Contact
                    2. Show all contacts
                    3. Show all contacts (sorted)
                    4. Exit
                    """);
            int userChoose = numbersScanner("Choose action: ", "Number required!");
            if (userChoose > 0 && userChoose < 4) {menuActions(userChoose, book, scanner);}
            else if (userChoose == 4) {work = false;}
            else System.out.println("Wrong action!");
        }
    }

    /**
     * Генерирует случайный мобильный номер
     * @param firstDigit Первая цифра номера
     * @param operatorCode Код оператора
     * @return Возвращает номер в формате Long
     */
    public static long generateRandomPhoneNumber(int firstDigit, int operatorCode) {
        long result = (firstDigit * 10000000000L) + (operatorCode * 10000000L);
        return result += new Random().nextInt(100,9999999);
    }

    /**
     * Получение числа от пользователя при вводе из консоли.
     * Проверяет на то, является ли введённые данные числом, и,
     * если проверка не пройдена, запускается повторный запрос
     * на ввод числа.
     * @param requestMessage Сообщение запроса
     * @param failMessage Сообщение при не верном вводе
     * @return Возвращает int
     */
    public static int numbersScanner(String requestMessage, String failMessage) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        boolean validNumber = false;
        while (!validNumber) {
            try {
                System.out.print("\u001B[32m" + requestMessage);
                number = scanner.nextInt();
                if (number < 0) {number *= -1;} // Преобразуем отрицательное в положительное
                validNumber = true;
            }
            catch (Exception e) {
                System.out.println("\u001B[31m" + failMessage);
                scanner.nextLine();
            }
        }
        return number;
    }

    /**
     * Список действий с телефонной книгой.
     * @param action Выбранное действие в виде пункта (int)
     * @param book Телефонная книга
     * @param scanner Сканер
     */
    public static void menuActions(int action, PhoneBook book, Scanner scanner) {
        switch (action) {
            case (1) -> {
                System.out.print("Input name: ");
                book.getContact(scanner.nextLine());
            }
            case (2) -> book.getAllContacts();
            case (3) -> book.getAllContactsSorted();
        }
    }

    /**
     * Сокращение для System.currentTimeMillis();
     * @return Возвращает long
     */
    public static long startTimer() {return System.currentTimeMillis();}

    /**
     * Форматированный вывод затраченного времени в секундах
     * @param start long
     */
    public static void stopTimer(long start) {
        long result = System.currentTimeMillis() - start;
        System.out.println(result / 1000f + "s");
        System.currentTimeMillis();
    }
}
