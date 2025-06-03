package courses.task4;

import java.sql.Date;

public class UserLogin {
    String userName;
    String name;
    String surname;
    String patronymic;
//    Integer userId;
    Date accessDate;
    String appType;

    public UserLogin() {
    }
    public UserLogin(String userName, String surname, String name, String patronymic, Date accessDate, String appType) {
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.accessDate = accessDate;
        this.appType = appType;
//        this.userId = userId;
    }

    public String getFio() {
        return surname + " " + name + " " + patronymic;
    }

    @Override
    public String toString() {
        return "UserLogin: userName=" + userName + " FIO=" + getFio() + " accessDate=" + accessDate + " appType=" + appType;
    }
}
