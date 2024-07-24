package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class PrinterTest {
    @Test
    void whenPrintMenuCorrectOutput() {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new Printer();

        menu.add(Menu.ROOT, "test1", System.out::println);
        menu.add(Menu.ROOT, "test2", System.out::println);
        menu.add("test1", "test1.1", System.out::println);
        menu.add("test1", "test2.1", System.out::println);
        menu.add("test1.1", "test1.1.1", System.out::println);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        printer.print(menu);

        System.setOut(originalOut);

        String expectedOutput = "1.test1\n"
                + "----1.1.test1.1\n"
                + "--------1.1.1.test1.1.1\n"
                + "----1.2.test2.1\n"
                + "2.test2\n";

        assertThat(outContent.toString()).isEqualToIgnoringNewLines(expectedOutput);
    }
}