package com.example.btl_web.dao;

import com.example.btl_web.mapper.RowMapper;

import java.util.List;

public interface GeneralDao {
    <T> List<T> selectSql(String sql, RowMapper<T> rowMapper, Object... parameters);
   <T> boolean updateSql(String sql, Object... parameters);
}
