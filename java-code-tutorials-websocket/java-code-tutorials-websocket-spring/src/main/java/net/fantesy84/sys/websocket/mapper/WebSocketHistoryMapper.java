package net.fantesy84.sys.websocket.mapper;

import java.util.List;
import net.fantesy84.sys.domain.WebSocketHistory;
import net.fantesy84.sys.domain.WebSocketHistoryExample;
import org.apache.ibatis.annotations.Param;

public interface WebSocketHistoryMapper {
    int countByExample(WebSocketHistoryExample example);

    int deleteByExample(WebSocketHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebSocketHistory record);

    int insertSelective(WebSocketHistory record);

    List<WebSocketHistory> selectByExampleWithBLOBs(WebSocketHistoryExample example);

    List<WebSocketHistory> selectByExample(WebSocketHistoryExample example);

    WebSocketHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebSocketHistory record, @Param("example") WebSocketHistoryExample example);

    int updateByExampleWithBLOBs(@Param("record") WebSocketHistory record, @Param("example") WebSocketHistoryExample example);

    int updateByExample(@Param("record") WebSocketHistory record, @Param("example") WebSocketHistoryExample example);

    int updateByPrimaryKeySelective(WebSocketHistory record);

    int updateByPrimaryKeyWithBLOBs(WebSocketHistory record);

    int updateByPrimaryKey(WebSocketHistory record);
}