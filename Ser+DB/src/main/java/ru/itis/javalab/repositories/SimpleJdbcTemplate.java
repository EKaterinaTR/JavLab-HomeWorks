package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate {
    private Connection connection;
    SimpleJdbcTemplate(Connection connection) {
        this.connection = connection;
    }
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object ... args) {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            if(args != null){
                preparedStatement = connection.prepareStatement(sql);
                int i = 1;
                for(Object o : args){
                    preparedStatement.setObject(1,o);
                    i++;
                }
                resultSet = preparedStatement.executeQuery();
            }
            else {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            }

            List<T> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    //ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
        }

    }
}
