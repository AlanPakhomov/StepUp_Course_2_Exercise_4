package courses.task4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserLoginDao {
    Connection con;

   /* public Integer findUserByNameFio(UserLogin user) throws SQLException{
        ResultSet resultSet = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select id from users where username = ? and fio = ?");
            stmt.setString(1, user.userName);
            stmt.setString(2, user.getFio());
            resultSet = stmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Ошибка добавления элемента в таблицу users");
            e.printStackTrace();
        }
        while(resultSet.next()) {
            System.out.println("Нашли- id="+resultSet.getString("id"));
            return resultSet.getInt("id");
        }
        return null;
    }
*/
    private void addUser(UserLogin user) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into users(username, fio) values (?,?) " +
                            "on conflict(username, fio) " +
                            "do update " +
                            "set username = ?, " +
                            " fio = ?");
            stmt.setString(1, user.userName);
            stmt.setString(2, user.getFio());
            stmt.setString(3, user.userName);
            stmt.setString(4, user.getFio());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Ошибка добавления элемента в таблицу users");
            e.printStackTrace();
        }
    }

    private void addLogin(UserLogin user) {
        ResultSet resultSet = null;
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into logins(user_id,access_date,application) " +
                            "values ((select usr.id from users usr where usr.username = ? and usr.fio = ?),?,?)");
            stmt.setString(1, user.userName);
            stmt.setString(2, user.getFio());
            stmt.setDate(3, user.accessDate);
            stmt.setString(4, user.appType);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Ошибка добавления элемента в таблицу logins");
            e.printStackTrace();
        }
    }

    public void addUserLogin(UserLogin user){
        addUser(user);
        addLogin(user);
    }

    public List<UserLogin> findAll() throws SQLException {
        ResultSet resultSet = null;
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users");
        } catch (Exception e) {
            System.out.println("Ошибка получения всех значений из users");
            e.printStackTrace();
        }
        List<UserLogin> users = new ArrayList<>();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("username"));
            System.out.println(resultSet.getString("fio"));
            //users.add(new UserLogin(resultSet.getLong("id"), resultSet.getString("username")));
        }
        return users;
    }

}
