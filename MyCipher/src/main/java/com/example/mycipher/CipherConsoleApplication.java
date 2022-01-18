package com.example.mycipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class CipherConsoleApplication implements CommandLineRunner {

    @Autowired
    private Cipher cipher;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        int choose = 0;
        while (choose != 1) {


            System.out.println("\n\tШифрования по Цезарю");
            System.out.println("\t--------------------");

            System.out.print("\nВведіть текст для шифрування: ");
            String text = scanner.nextLine();
            System.out.print("Введіть число , на скільки буде здвинуто кожну букву в тексті: ");
            long offset = 0;
            try {
                offset = scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Ви ввели не число");

            }
            System.out.println("Результат шифру: " +cipher.encrypt(text, offset) + "\n");

            System.out.println("Щоб вийти з циклу введіть - 1 ");
            System.out.println("Щоб продовжити введіть - будь-що ");
            System.out.print("Ваш вибір: ");

            try {
                choose = scanner.nextInt();
            }
            catch (InputMismatchException e) {

            }

        }
    }
}
