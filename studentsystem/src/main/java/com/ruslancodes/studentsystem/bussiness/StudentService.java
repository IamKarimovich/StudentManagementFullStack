package com.ruslancodes.studentsystem.bussiness;

import com.ruslancodes.studentsystem.entities.Student;
import result.Result;

import java.util.List;

public interface StudentService {

    Result saveStudent(Student student);
    List<Student> getStudents();

    Result deleteStudent(String name,String surName,String studentNo);
    boolean existsStudent(String name,String surName,String studentNo);

    Result editStudent(String studentNo,Student student);

    Student findStudentByStudentNo(String studentNo);

    Result deleteStudentWithStudentNo(String StudentNo);
}
