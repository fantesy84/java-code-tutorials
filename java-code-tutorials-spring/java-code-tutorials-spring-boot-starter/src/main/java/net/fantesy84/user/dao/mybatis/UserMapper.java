package net.fantesy84.user.dao.mybatis;

import java.util.List;
import net.fantesy84.user.domain.User;
import net.fantesy84.user.domain.UserExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    @Delete({
        "delete from GB_SYS_USER",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into GB_SYS_USER (ACCOUNT, PASSWORD, ",
        "SEX, REAL_NAME, NICK_NAME, ",
        "BIRTHDAY, DELETE_FLAG)",
        "values (#{account,jdbcType=VARCHAR}, #{password,jdbcType=CHAR}, ",
        "#{sex,jdbcType=CHAR}, #{realName,jdbcType=VARCHAR}, #{nickName,jdbcType=VARCHAR}, ",
        "#{birthday,jdbcType=DATE}, #{deleteFlag,jdbcType=BIT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    @Select({
        "select",
        "ID, ACCOUNT, PASSWORD, SEX, REAL_NAME, NICK_NAME, BIRTHDAY, DELETE_FLAG",
        "from GB_SYS_USER",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    @Update({
        "update GB_SYS_USER",
        "set ACCOUNT = #{account,jdbcType=VARCHAR},",
          "PASSWORD = #{password,jdbcType=CHAR},",
          "SEX = #{sex,jdbcType=CHAR},",
          "REAL_NAME = #{realName,jdbcType=VARCHAR},",
          "NICK_NAME = #{nickName,jdbcType=VARCHAR},",
          "BIRTHDAY = #{birthday,jdbcType=DATE},",
          "DELETE_FLAG = #{deleteFlag,jdbcType=BIT}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}