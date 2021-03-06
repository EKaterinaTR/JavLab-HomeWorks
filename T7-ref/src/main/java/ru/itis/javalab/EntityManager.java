package ru.itis.javalab;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class EntityManager {
    JdbcTemplate jdbcTemplate;

    public EntityManager(JdbcTemplate j) {
        this.jdbcTemplate = j;
    }


    public <T> void createTable(String tableName, Class<T> entityClass) {
        jdbcTemplate.execute(getSqlCreateTable(tableName, entityClass));

    }

    private <T> String getSqlCreateTable(String tableName, Class<T> entityClass) {
        String sql = "create table " + tableName + "(";
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            sql += getColumn(fields[i]);
            if (i != fields.length - 1) {
                sql += ",";
            }
        }
        sql += ");";
        return sql;

    }

    private String getColumn(Field field) {
        String column = field.getName();
        column += getType(field);
        return column;
    }

    private String getType(Field field) {
        String s = field.getType().getSimpleName();
        String type = "";
        switch (s) {
            case "Long":
            case "long":
                type = " bigint";
                break;
            case "Integer":
            case "int":
                type = " integer";
                break;
            case "boolean":
            case "Boolean":
                type = " boolean";
                break;
            case "String":
                type = " varchar";
                break;
            case "Float":
            case "float":
                type = " real";
                break;
            case "Double":
            case "double":
                type = " double precision";
                break;
            default:
                type = " varchar";

        }
        return type;
    }


    public void save(String tableName, Object entity) {
        jdbcTemplate.execute(getSqlInsert(tableName, entity));
    }

    private String getSqlInsert(String tableName, Object object) {
        Class<?> classOfEntity = object.getClass();
        return getFirstPartOfSql(tableName, classOfEntity) + getSecondPartOfSql(object);
    }

    private <T> String getFirstPartOfSql(String tableName, Class<T> tClass) {
        String firstPartOfSql = "insert into " + tableName + "(";

        Field[] fields = tClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            firstPartOfSql += " " + fields[i].getName();
            if (i < fields.length - 1) {
                firstPartOfSql += ",";
            }
        }
        return firstPartOfSql + ") ";

    }

    private <T> String getSecondPartOfSql(Object entity) {
        Class<?> classOfEntity = entity.getClass();
        String secondPartOfSql = "values (";
        Field[] fields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            secondPartOfSql += getValue(fields[i], entity);

            if (i < fields.length - 1) {
                secondPartOfSql += ",";
            }
        }

        return secondPartOfSql + ");";
    }

    private String getValue(Field field, Object entity) {
        String s = field.getType().getSimpleName();
        String value = "";
        field.setAccessible(true);
        try {
            value = field.get(entity).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        switch (s) {
            case "Long":
            case "long":
            case "Integer":
            case "int":
            case "boolean":
            case "Boolean":
            case "Float":
            case "float":
            case "Double":
            case "double":
                break;
            case "String":
            default:
                value = "\'" + value + "\'";
                break;

        }
        return value;
    }


    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {
        return jdbcTemplate.query(getSQLFindById(tableName, idType, idValue), getMapper(resultType)).get(0);
    }

    private <T> RowMapper<T> getMapper(Class<T> resultType) {

        RowMapper<T> entityRowMapper = (row, rowNumber) -> {
            T entity = null;
            try {
                entity = resultType.newInstance();
            } catch (InstantiationException e) {
                throw new IllegalStateException(e);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            }

            for (Field field : resultType.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    field.set(entity, row.getObject(field.getName().toLowerCase()));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
            return entity;
        };

        return entityRowMapper;
    }

    private <ID> String getSQLFindById(String tableName, Class<ID> idType, ID idValue) {
        String sql = "select * from "
                + tableName + " where id = "
                + getIdInCorrectForm(idType, idValue)
                + " limit 1;";
        return sql;
    }

    private <ID> String getIdInCorrectForm(Class<ID> idType, ID idValue) {
        return (idType.equals(String.class) ? "\'" + idValue + "\'" : "" + idValue);
    }
}
