package login.mapper;

import login.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  //通过用户名密码查询用户
    @Select("select * from user where user_name =#{user_name} and password=#{password}")
    User selectUserByNP(@Param("user_name")String user_name,@Param("password")String password);
  //通过用户名查询用户
  @Select("select * from user where user_name =#{user_name}")
  User selectUserByName(@Param("user_name")String user_name);
    //新建用户
    @Insert("insert into user(user_name,password,role_id) values (#{user_name},#{password},#{role_id})")
    int createUser(User user);

}
