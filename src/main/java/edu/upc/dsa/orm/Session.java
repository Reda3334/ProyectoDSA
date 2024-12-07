package edu.upc.dsa.orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Session {

    void save(Object entity) throws SQLException;
    void close();
    <T> T get(Class<T> theClass, int ID) throws SQLException;
    <T> void update(Class<T> theClass, Map<String, Object> changes, Map<String, Object> selectors) throws SQLException;
    <T> void delete(Class<T> theClass, int id) throws SQLException;
    <T> void delete(Class<T> theClass, Map<String, Object> params) throws SQLException;
    <T> List<T> findAll(Class<T> theClass) throws SQLException;
    <T> List<T> findAll(Class<T> theClass, Map<String, Object> params) throws SQLException;

}
