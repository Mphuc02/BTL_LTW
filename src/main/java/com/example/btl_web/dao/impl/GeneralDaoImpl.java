package com.example.btl_web.dao.impl;

import com.example.btl_web.dao.GeneralDao;
import com.example.btl_web.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GeneralDaoImpl<T> implements GeneralDao {
    ResourceBundle bundle = ResourceBundle.getBundle("data_base");
    public Connection getConnection()
    {
        String url = bundle.getString("url");
        String userName = bundle.getString("userName");
        String passWord = bundle.getString("password");
        Connection connection = null;
        try {
            Class.forName(bundle.getString("driverName"));
            connection = DriverManager.getConnection(url, userName, passWord);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public <T> List<T> selectSql(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();

        Connection connection = getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            //Lấy câu sql statement
            statement = connection.prepareStatement(sql);
            //Truyền các tham số
            setParameters(statement, parameters);

            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                //Tạo 1 đối tượng từ resultset
                T element = rowMapper.mapper(resultSet);
                results.add(element);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            clossConnect(connection, statement, resultSet);
        }

        return results;
    }
    @Override
    public <T> boolean updateSql(String sql, Object... parameters)
    {
        Integer resultSet = null;
        PreparedStatement statement = null;
        Connection connection = getConnection();

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            //Truyền các tham số
            setParameters(statement, parameters);
            //Thực thi sql
            statement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                return false;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        clossConnect(connection, statement, null);
        return true;
    }
    private void setParameters(PreparedStatement statement, Object... parameters)
    {
        for(int i = 0 ; i < parameters.length; i++)
        {
            int index = i + 1;
            Object object = parameters[i];

            try {
                if (object instanceof Long)
                {
                    statement.setLong(index, (Long) object);
                }
                else if(object instanceof Integer)
                {
                    statement.setInt(index, (Integer) object);
                }
                else if(object instanceof String)
                {
                    statement.setString(index, (String) object);
                }
                else if (object instanceof Double)
                {
                    statement.setDouble(index, (Double) object);
                }
                else if(object instanceof Float)
                {
                    statement.setFloat(index, (Float) object);
                }
                else if (object instanceof Date)
                {
                    statement.setDate(index, (Date) object);
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clossConnect(Connection connection, PreparedStatement statement, ResultSet resultSet)
    {
        //Đóng các kết nối
        if(connection != null) {
            try {
                connection.close();
                if(statement != null)
                    statement.close();
                if(resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
