package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            System.out.println(menuItemInfo.getNumber() + menuItemInfo.getName());
        }
    }
}
