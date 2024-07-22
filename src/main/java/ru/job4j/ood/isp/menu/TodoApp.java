package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {

    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        Printer printer = new Printer();
        Scanner scanner = new Scanner(System.in);

        boolean cycle = true;
        while (cycle) {
            System.out.println(""" 
                Меню:
                1. Добавить элемент в корень меню.
                2. Добавить элемент к родительскому элементу.
                3. Вызвать действие, привязанное к пункту меню.
                4. Вывести меню в консоль.
                5. Выход.
                """);
            int number = Integer.parseInt(scanner.nextLine());
            switch (number) {
                case 1 -> addRootItem(menu, scanner);
                case 2 -> addChildItem(menu, scanner);
                case 3 -> selectItem(menu, scanner);
                case 4 -> printer.print(menu);
                case 5 -> cycle = false;
                default -> System.out.println("Неверный ввод. Попробуйте снова.");

            }
        }
    }

    public static void addRootItem(Menu menu, Scanner scanner) {
        System.out.print("Введите название корневого элемента: ");
        String name = scanner.nextLine();
        menu.add(Menu.ROOT, name, DEFAULT_ACTION);
        System.out.println("Корневой элемент добавлен");
    }

    public static void addChildItem(Menu menu, Scanner scanner) {
        System.out.print("Введите название корневого элемента: ");
        String nameParent = scanner.nextLine();
        System.out.print("Введите название дочернего элемента: ");
        String nameChild = scanner.nextLine();
        menu.add(nameParent, nameChild, DEFAULT_ACTION);
        System.out.println("Дочерний элемент добавлен");
    }

    public static void selectItem(Menu menu, Scanner scanner) {
        System.out.print("Введите название для вызова действия: ");
        String itemName = scanner.nextLine();
        menu.select(itemName).ifPresentOrElse(
                itemInfo -> itemInfo.getActionDelegate().delegate(),
                () -> System.out.println("Элемент не найден")
        );
    }
}
