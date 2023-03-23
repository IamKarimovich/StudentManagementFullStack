package com.ruslancodes.studentsystem.bussiness;

import com.ruslancodes.studentsystem.dataAcces.StudentRepository;
import com.ruslancodes.studentsystem.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.Result;

import java.util.List;
import java.util.Optional;

@Service
public class StudentManager implements StudentService {


    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Result saveStudent(Student student) {
        if(!studentRepository.existsStudentByStudentNo(student.getStudentNo()))
        {
            this.studentRepository.save(student);
            return new Result("Successfully addition!",true);
        }
        return new Result("Student number is already exists!",false);
    }

    @Override
    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }
    @Override
    public Result deleteStudentWithStudentNo(String studentNo) {
        if(this.studentRepository.existsStudentByStudentNo(studentNo))
        {
            this.studentRepository.deleteStudentByStudentNo(studentNo) ;
            return new Result("Successfully delete operation",true);
        }

        return new Result("Student couldn't found!",false);
    }

    @Override
    public Result deleteStudent(String name, String surName, String studentNo) {

        if(existsStudent(name,surName,studentNo)) {

            this.studentRepository.deleteStudentByNameAndSurNameAndStudentNo(name, surName, studentNo);
            return new Result("Successfully delete operation!",true);
        }
        return new Result("Student couldn't found",false);
    }

    @Override
    public boolean existsStudent(String name, String surName, String studentNo) {
        return this.studentRepository.existsStudentByNameAndSurNameAndStudentNo(name,surName,studentNo);
    }

    @Override
    public Result editStudent(String studentNo, Student student) {

        if(studentRepository.existsStudentByStudentNo(studentNo))
        {
            int id = studentRepository.findStudentByStudentNo(studentNo).getId();
            Optional<Student> st = this.studentRepository.findById(id);

            if(st.isPresent())
            {
                st.get().setName(student.getName());
                st.get().setSurName(student.getSurName());
                st.get().setStudentNo(student.getStudentNo());
                st.get().setAddress(student.getAddress());
                st.get().setPassword(student.getPassword());
                st.get().setLogin(student.getLogin());
                st.get().setBirthDate(student.getBirthDate());

                if(student.getStudentNo().equals(studentNo))
                {
                    studentRepository.save(st.get());
                    return new Result("Student has succesfully updated!",true);
                }
                else{
                    if(studentRepository.existsStudentByStudentNo(student.getStudentNo()))
                    {
                        return new Result("Student number is already exist",false);
                    }else{
                        studentRepository.save(st.get());
                        return new Result("Student has succesfully updated!",true);
                    }
                }

            }
        }

        return new Result("Student couldn't update!",false);

    }

    @Override
    public Student findStudentByStudentNo(String studentNo) {
        if(studentRepository.existsStudentByStudentNo(studentNo))
        {
            return studentRepository.findStudentByStudentNo(studentNo);
        }
        return null;

    }
}
