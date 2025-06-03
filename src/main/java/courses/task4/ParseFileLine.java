package courses.task4;

import org.springframework.stereotype.Component;

@Component
public class ParseFileLine {

    public static String[] parseLine(String input) {
        String[] words = splitToWords(input);
/*
        System.out.println("Количество слов: " + words.length);
     //   for (String word : words) {
            System.out.println(words[0]);
            System.out.println(words[1]);
            System.out.println(words[2]);
            System.out.println(words[3]);
            System.out.println(words[4]);
            System.out.println(words[5]);
      //  }
*/
        return words;
    }

    /**
     * Метод, который принимает строку и возвращает массив её слов,
     * разделяя по пробелам.
     *
     * @param text исходная строка
     * @return массив слов строки
     */
    private static String[] splitToWords(String text) {
        return text.split("\\s+");
    }
}