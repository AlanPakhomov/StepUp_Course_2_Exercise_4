package courses.task4;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileScanner {

    public static List<String> scanDirectory(String directoryPath) throws Exception {

        File dir = new File(directoryPath);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("Указанный путь "+directoryPath+" не является существующей директорией");
        }

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return new ArrayList<>();
        }

        List<String> resultLines = new ArrayList<>();

        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    resultLines.add(line);
                }
            }
        }

        return resultLines;
    }

    public static String inputDirectoryName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название папки с файлами: ");
        return scanner.nextLine();
    }

}