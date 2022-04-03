package io.github.acezhuang.fx;

import java.sql.*;

public class JdbcDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/db1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
        Connection connection = DriverManager.getConnection(url, "root", "root");
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from user");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("结果：" + resultSet.getString("name"));
        }
    }
}
