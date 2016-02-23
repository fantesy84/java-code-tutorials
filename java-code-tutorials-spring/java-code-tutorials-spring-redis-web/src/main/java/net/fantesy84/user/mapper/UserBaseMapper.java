package net.fantesy84.user.mapper;

import java.util.List;
import net.fantesy84.user.domain.UserBase;
import net.fantesy84.user.domain.UserBaseExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface UserBaseMapper {
    int countByExample(UserBaseExample example);

    int deleteByExample(UserBaseExample example);

    @Delete({
        "delete from user_base",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_base (account, password, ",
        "email, telphone)",
        "values (#{account,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{telphone,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(UserBase record);

    int insertSelective(UserBase record);

    List<UserBase> selectByExample(UserBaseExample example);

    @Select({
        "select",
        "id, account, password, email, telphone",
        "from user_base",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserBase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserBase record, @Param("example") UserBaseExample example);

    int updateByExample(@Param("record") UserBase record, @Param("example") UserBaseExample example);

    int updateByPrimaryKeySelective(UserBase record);

    @Update({
        "update user_base",
        "set account = #{account,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "telphone = #{telphone,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserBase record);
}