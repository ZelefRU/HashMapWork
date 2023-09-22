package org.example;

import java.util.*;

/**
 * Телефонная книжка
 * @author Zelef (Важенин Никита)
 */
class PhoneBook {
    private final HashMap<String, Long[]> book = new HashMap<>();
    private boolean debug = false;

    /**
     * Режим Debug
     * @param state режим true/false
     */
    public void setDebug(boolean state) {debug = state;}

    /**
     * Метод отправки Debug сообщений
     * @param message содержимое сообщения
     */
    public void msgDebug(String message) {if (debug) System.out.println(Color.GREEN + " [i] "+ message);}

    /**
     * Добавление контакта в телефонный справочник (книгу)
     * @param name имя в формате String
     * @param number мобильный номер телефона в формате long
     */
    public void addContact(String name, long number) {
        // Проверяем содержится ли такое имя в книге
        msgDebug(" - addPhone method has been called");
        if (book.containsKey(name)) {
            msgDebug(Color.RED.getCode() + "book already contains " + Color.BLUE.getCode() + name);
            // Проверяем содержится ли номер если есть имя
            if (book.get(name)[0].equals(number)) {
                System.out.printf(Color.RED.getCode() + "User already created! (%s, %s)\n", name, number);
            }
            // Если есть запись, но номера разные, то добавляем в массив Long ещё один номер
            else {
                msgDebug("new number is different");
                msgDebug("add new number - " + Color.LIGHT_BLUE.getCode() + number);
                Long[] temp = new Long[book.get(name).length + 1];
                for (int i = 0; i < temp.length - 1; i++) {
                    temp[i] = book.get(name)[i];
                }
                temp[book.get(name).length] = number;
                book.put(name, temp);
            }
        }
        // Если имени нет, то просто добавляем пользователя в базу
        else {
            book.put(name, new Long[]{number});
            msgDebug(String.format("new user has been added - %s %s, %s",Color.LIGHT_BLUE.getCode(), name, number));
        }
    }

    /**
     * Вывод контакта в консоль
     * @param name имя (ключ)
     */
    public void getContact(String name) {
        // Получаем список номеров человека
        msgDebug(" - getContact method has been called");
        Long[] numbers = book.get(name);
        // Добавляем окончание 's если номеров больше одного
        String wordEnding = "";
        if (numbers.length > 1) {
            wordEnding = "s";
        }
        // Создаём результирующую строку
        StringBuilder result = new StringBuilder(String.format(Color.RESET.getCode() + "Name: %s. Number%s: ",name, wordEnding));
        // Добавляем номера
        for (Long number : numbers) {
            result.append(number.toString()).append(", ");
        }
        // Удаляем ", " в конце
        result.setLength(result.length() - 2);
        // Выводим результат
        System.out.println(result);
    }

    /**
     * Вывод всех контактов, записанных в телефонную книгу
     */
    public void getAllContacts() {
        msgDebug(" - getAllContacts method has been called");
        System.out.println(Color.RESET.getCode() + "-".repeat(10) +  "Contact list" + "-".repeat(10));
        book.forEach((s, longs) -> {
            System.out.printf("\u001B[35m" + "Name: %s, Numbers: ", s);
            System.out.println(Arrays.toString(longs));
        });
    }

    /**
     * Вывод всех контактов из телефонной книги с сортировкой
     * по возрастанию номера телефона. Учитывается наличие нескольких номеров.
     */
    public void getAllContactsSorted() {
        msgDebug(" - getAllContactsSorted method has been called");
        System.out.println(Color.RESET.getCode() + "-".repeat(10) + "Contact Sorted list" + "-".repeat(10));
        // Создаём лист и сохраняем в него значения для сортировки
        List<Map.Entry<String, Long[]>> entries = new ArrayList<>(book.entrySet());

        // Сортируем номера внутри каждого массива (если у пользователя несколько номеров)
        entries.forEach(entry -> Arrays.sort(entry.getValue()));
        // Сортируем по первому номеру
        entries.sort(Comparator.comparing(entry -> entry.getValue()[0]));
        // Вывод
        for (Map.Entry<String, Long[]> entry: entries) {
            Long[] values = entry.getValue();
            System.out.printf(Color.LIGHT_BLUE.getCode() + "Name: %s, Number: %s%n", entry.getKey(), Arrays.toString(values));
        }
    }
}