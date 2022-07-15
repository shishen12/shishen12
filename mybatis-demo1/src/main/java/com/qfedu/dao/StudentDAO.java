package com.qfedu.dao;

import com.qfedu.pojo.Student;

import java.util.HashMap;
import java.util.List;

public interface StudentDAO {

    public int insertStudent(Student student);

    public int deleteStudent(String stuNum);

    public int updateStudent(Student student);

    public List<Student> listStudents();

    public Student queryStudent(String stuNum);

    public  List<Student> listStudentsByPage(HashMap<String,Integer> map);

}
