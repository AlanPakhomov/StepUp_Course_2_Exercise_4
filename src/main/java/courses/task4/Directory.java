package courses.task4;

import java.util.Scanner;

public class Directory {
    String nameDir;

    public String inputDirectoryName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название папки с файлами: ");
        nameDir = scanner.nextLine();

        return nameDir;
    }

}
