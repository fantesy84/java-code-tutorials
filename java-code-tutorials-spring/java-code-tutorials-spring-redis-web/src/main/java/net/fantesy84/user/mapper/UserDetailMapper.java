package net.fantesy84.user.mapper;

import java.util.List;
import net.fantesy84.user.domain.UserDetail;
import net.fantesy84.user.domain.UserDetailExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDetailMapper {
    int countByExample(UserDetailExample example);

    int deleteByExample(UserDetailExample example);

    @Delete({
        "delete from user_detail",
        "where baseId = #{baseid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer baseid);

    @Insert({
        "insert into user_detail (baseId, real_name, ",
        "nick_name, birthday, ",
        "sex, post_code, address)",
        "values (#{baseid,jdbcType=INTEGER}, #{realName,jdbcType=VARCHAR}, ",
        "#{nickName,jdbcType=VARCHAR}, #{birthday,jdbcType=TIMESTAMP}, ",
        "#{sex,jdbcType=CHAR}, #{postCode,jdbcType=VARCHAR}, #{address,jdbcType=LONGVARCHAR})"
    })
    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    List<UserDetail> selectByExampleWithBLOBs(UserDetailExample example);

    List<UserDetail> selectByExample(UserDetailExample example);

    @Select({
        "select",
        "baseId, real_name, nick_name, birthday, sex, post_code, address",
        "from user_detail",
        "where baseId = #{baseid,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    UserDetail selectByPrimaryKey(Integer baseid);

    int updateByExampleSelective(@Param("record") UserDetail record, @Param("example") UserDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") UserDetail record, @Param("example") UserDetailExample example);

    int updateByExample(@Param("record") UserDetail record, @Param("example") UserDetailExample example);

    int updateByPrimaryKeySelective(UserDetail record);

    @Update({
        "update user_detail",
        "set real_name = #{realName,jdbcType=VARCHAR},",
          "nick_name = #{nickName,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=TIMESTAMP},",
          "sex = #{sex,jdbcType=CHAR},",
          "post_code = #{postCode,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=LONGVARCHAR}",
        "where baseId = #{baseid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(UserDetail record);

    @Update({
        "update user_detail",
        "set real_name = #{realName,jdbcType=VARCHAR},",
          "nick_name = #{nickName,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=TIMESTAMP},",
          "sex = #{sex,jdbcType=CHAR},",
          "post_code = #{postCode,jdbcType=VARCHAR}",
        "where baseId = #{baseid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserDetail record);
}