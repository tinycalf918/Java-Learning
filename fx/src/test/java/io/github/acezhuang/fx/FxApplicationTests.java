package io.github.acezhuang.fx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
class FxApplicationTests {

    @Autowired
    Student student;
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {
        student.print();
    }

    @Test
    void test1() {
        student.print();
    }

    @Test
    void testDb() throws SQLException {
        System.out.println(dataSource.getClass().getName());

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
    }


}
