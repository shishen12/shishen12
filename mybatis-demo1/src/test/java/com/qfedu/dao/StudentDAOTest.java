package com.qfedu.dao;

import com.qfedu.pojo.Student;
import com.qfedu.utils.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StudentDAOTest {

    @org.junit.Test
    public void insertStudent() {
        SqlSession sqlSession= MyBatisUtil.getSqlSession();
        try{
            StudentDAO studentDAO=sqlSession.getMapper(StudentDAO.class);
            Student student = new Student(0, "10010", "张三dd", "男", 21);
            int i=studentDAO.insertStudent(student);
            System.out.println(student);
            System.out.println(i);
            sqlSession.commit();
        }catch (Exception e){
            sqlSession.rollback();
        }


    }

    @org.junit.Test
    public void deleteStudent() {
        StudentDAO studentDAO=MyBatisUtil.getMapper(StudentDAO.class);
        int i=studentDAO.deleteStudent("1");

    }
    @Test
    public  void  testUpdateStudent(){
        StudentDAO studentDAO=MyBatisUtil.getMapper(StudentDAO.class);
        Student student = new Student(0, "10006", "李斯dd", "女", 18);
        int i=studentDAO.updateStudent(student);
        assertEquals(1,i);


    }
    @Test
    public void testListStudents(){
        StudentDAO studentDAO = MyBatisUtil.getMapper(StudentDAO.class);
        List<Student> list = studentDAO.listStudents();
            //System.out.println(Arrays.toString(list.toArray()));
            for (Student stu:list) {
                System.out.println(stu);
            }

    }
    @Test
    public void testQueryStudent(){
        try {
            InputStream is=Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory=builder.build(is);
            SqlSession sqlSession=factory.openSession();
            StudentDAO studentDAO=sqlSession.getMapper(StudentDAO.class);
            Student student=studentDAO.queryStudent("10001");
            assertNotNull(student);
            System.out.println(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public  void testListStudentsByPage(){
        try {
            InputStream is=Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory=builder.build(is);
            SqlSession sqlSession=factory.openSession();
            StudentDAO studentDAO=sqlSession.getMapper(StudentDAO.class);
            HashMap<String,Integer> map=new HashMap<>();
            map.put("start",0);
            map.put("pageSize",2);

            List<Student> list = studentDAO.listStudentsByPage(map);
            assertNotNull(list);
            //System.out.println(Arrays.toString(list.toArray()));
            for (Student stu:list) {
                System.out.println(stu);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}