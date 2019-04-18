package org.oasis.springbootdemo.dao;

import org.apache.ibatis.annotations.*;
import org.oasis.springbootdemo.model.Student;

import java.util.List;
import java.util.Map;

/**
 * @author tianbo
 * @date 2019-02-22
 */
public interface StudentDao {
    @Select("select * from student where id = #{id}")
    Student findById(@Param("id") Integer id);

    @Insert("insert into student(name,sno,major,grade, create_time, update_time) values (#{name},#{sno},#{major}," +
            "#{grade},#{createTime},#{updateTime})")
    int insert(Student student);

    @Delete("delete from student where id = #{id}")
    int delete(@Param("id") Integer id);

    @Update("update student set name = #{name} where id = #{id}")
    int update(@Param("id") Integer id, @Param("name") String name);

    @Select("select * from student limit 100")
    List<Student> list();

    @Select("select id, name from student")
    List<Map<Integer, String>> selectIdName();
}
