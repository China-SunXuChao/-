package com.bdqn.log.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teach.entity.log.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface OperationLogMapper extends BaseMapper<SysLog> {
    @Select("<script>" +
            "select " +
            "count(0) " +
            "from " +
            "t_log " +
            "</script>")
    long getTotal(Map<String, Object> map);

    @Select("<script>" +
            "select * " +
            "from t_log " +
            "limit #{i},#{size} " +
            "</script>")
    List<SysLog> getLogMonitoring(int i, Integer size, Map<String, Object> map);
}
