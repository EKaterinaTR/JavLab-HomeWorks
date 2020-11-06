package ru.itis.javalab;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.javalab.repositories.SimpleJdbcTemplate;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class EntityManager {
    SimpleJdbcTemplate simpleJdbcTemplate;

    public EntityManager(SimpleJdbcTemplate simpleJdbcTemplate) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
    }


    public <T> void createTable(String tableName, Class<T> entityClass) {
        simpleJdbcTemplate.queryWithoutAnswer(getSqlCreateTable(tableName, entityClass));

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
            default:
                Optional<Field> field1 = Arrays.stream(field.getClass().getFields())
                        .filter(x -> x.getName().equals("id") || x.getName().equals("ID"))
                        .findAny();
                if (field1.isPresent()) {
                    type = getType(field1.get());
                } else {
                    type = " varchar";
                }
        }
        return type;
    }


    public void save(String tableName, Object entity) {
        // сканируем его поля
        // сканируем значения этих полей
        // генерируем insert into
        simpleJdbcTemplate.queryWithoutAnswer(getSqlInsert(tableName, entity));
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
                break;
            case "String":
                value = "\'" + value + "\'";
                break;
            default:
                Optional<Field> field1 = Arrays.stream(field.getClass().getFields())
                        .filter(x -> x.getName().equals("id") || x.getName().equals("ID"))
                        .findAny();
                if (!field1.isPresent()) {
                    value = "\'" + value + "\'";
                }

        }
        return value;
    }


    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {

        return simpleJdbcTemplate.query(getSQLFindById(tableName, idType, idValue),
                getMapper(resultType)).get(0);
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
