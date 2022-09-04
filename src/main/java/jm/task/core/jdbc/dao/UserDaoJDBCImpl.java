//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//
//    public UserDaoJDBCImpl() {
//    }
//
//    private Connection connection = Util.open();
//
//    public void createUsersTable() {
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate(" CREATE TABLE IF NOT EXISTS user(\n" +
//                    "  id INT PRIMARY KEY AUTO_INCREMENT,\n" +
//                    "  name VARCHAR(30),\n" +
//                    "  lastName VARCHAR(30),\n" +
//                    "  age INT\n" +
//                    "  );");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void dropUsersTable() {
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate("DROP TABLE IF EXISTS user");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        try (Statement statement = connection.createStatement()) {
//            PreparedStatement prepStat = connection.prepareStatement("INSERT  user " +
//                    "(name, lastName, age) VALUES (?,?,?);");
//            prepStat.setString(1, name);
//            prepStat.setString(2, lastName);
//            prepStat.setInt(3, age);
//            prepStat.executeUpdate();
//            prepStat.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removeUserById(long id) {
//        try (Statement statement = connection.createStatement()) {
//            PreparedStatement prepStat = connection.prepareStatement("DELETE FROM user WHERE id = ?");
//            prepStat.setLong(1, id);
//            prepStat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> user_list = new ArrayList<>();
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = connection.prepareStatement("SELECT * FROM user").executeQuery();
//            while (resultSet.next()) {
//                User user = new User(resultSet.getLong("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("lastName"),
//                        resultSet.getByte("age"));
//                user_list.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user_list;
//    }
//
//    public void cleanUsersTable() {
//        try (Statement statement = connection.createStatement()) {
//            PreparedStatement preparedState = connection.prepareStatement("TRUNCATE TABLE user");
//            preparedState.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
