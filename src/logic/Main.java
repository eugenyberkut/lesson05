package logic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Eugeny on 07.06.2015.
 */
public class Main {

    List<Toy> toys = new ArrayList<Toy>();

    public static final String FILE_NAME = "toys1.txt";

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        loop: for(;;) {
            switch (menu()) {
                case 1:
                    writeFile(); break;
                case 2:
                    readFile(); break;
                case 3:
                    showToys(); break;
                case 4:
                    writeBinary(); break;
                case 5:
                    readBinary(); break;
                case 6: // Реализовать чтение текстового файла в стиле Java 8
                    readFile8();
                case 0: break loop; // прервать цикл с меткой loop:
            }
        }

    }

    private void readFile8() {
        // реализовать
    }

    private void readBinary() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("toys.dat"))){
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("toys.dat"));
            toys = (List<Toy>) ois.readObject();
            //ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeBinary() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("toys.dat"))){
            //ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("toys.dat"));
            oos.writeObject(toys);
            // oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showToys() {
        //toys.forEach(System.out::println);
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }

    private void readFile()  /* throws IOException */ {
        toys.clear();
        try {
            BufferedReader in = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = in.readLine()) != null) {
                Toy t = Toy.parseToy(line);
                toys.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int menu() {
        System.out.println("1. Запись в файл");
        System.out.println("2. Чтение из файла");
        System.out.println("3. Вывод на экран");
        System.out.println("4. Запись в бинарный файл");
        System.out.println("5. Чтение бинарного файла");
        System.out.println("0. Выход");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private void writeFile() {
        String kukla = "Doll";
        Toy t1 = new Toy(kukla, "doll", 2, 5, 100, 30, "cm");
        Toy t2 = new Toy(kukla, "doll", 3, 15, 120, 30, "cm");

        toys.add(t1);
        toys.add(t2);
        try (PrintWriter pw = new PrintWriter(FILE_NAME)){
            for (Toy toy : toys) {
                pw.println(toy);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        try {
//            PrintWriter pw = new PrintWriter("toys.txt");
//            for (Toy toy : toys) {
//                pw.println(toy);
//            }
//            pw.close(); // close в верху закрывается автоматически
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
