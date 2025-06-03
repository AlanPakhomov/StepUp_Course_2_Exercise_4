package courses.task4;

import java.sql.Date;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.List;

public class Starter {
    public static void main(String[] args) throws Exception {
        final String pathDir = "c:\\Work\\!ОБУчЕНИЕ - StepUpStudy- JAVA\\Задание№4 Spring\\";

        String dirName = FileScanner.inputDirectoryName();
System.out.println("dirName ="+dirName );
        List<String> fileStrings = FileScanner.scanDirectory(pathDir + dirName);

        UserLoginDao usersDao = new UserLoginDao();
        usersDao.con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",      // Логин пользователя
                "postgres"            // Пароль пользователя
        );

        UserLogin user = new UserLogin();//"apaHomov","Пахомов","Алан", "Александрович", Date.valueOf("2025-05-21"),"mobil");
        for (String fileStr : fileStrings) {
            String[] words = ParseFileLine.parseLine(fileStr);
            user.userName = words[0];
            user.name = words[1];
            user.surname = words[2];
            user.patronymic = words[3];
            System.out.println("words[4]="+words[4]);
            user.accessDate =  Date.valueOf(LocalDate.parse(words[4]));
            user.appType = words[5];
            usersDao.addUserLogin(user);
        }

        usersDao.findAll();
    }
}
