package ru.itis.javalab.repositories;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate {

    private DataSource dataSource;
    SimpleJdbcTemplate(DataSource source) {
        dataSource = source;
    }
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object ... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for(Object o : args){
                preparedStatement.setObject(i,o);
                i++;
            }
            resultSet = preparedStatement.executeQuery();
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
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
        }

    }

    public void queryWithoutAnswer(String sql, Object ... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for(Object o : args){
                preparedStatement.setObject(i,o);
                i++;
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
        }

    }

}
