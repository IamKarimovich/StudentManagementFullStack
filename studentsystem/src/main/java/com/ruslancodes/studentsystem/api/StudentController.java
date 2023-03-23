package com.ruslancodes.studentsystem.api;


import com.ruslancodes.studentsystem.bussiness.StudentService;
import com.ruslancodes.studentsystem.dataAcces.StudentRepository;
import com.ruslancodes.studentsystem.entities.Student;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import result.Result;

import java.util.List;


@RestController
@RequestMapping("/student")
@NoArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public Result add(@RequestBody Student student)
    {
        return this.studentService.saveStudent(student);
    }

    @GetMapping("/getall")
    public List<Student> getAll()
    {
        return studentService.getStudents();
    }

    @DeleteMapping(path = "/withNo/{studentNo}")
    public Result deleteWithStudentNo(@PathVariable String studentNo)
    {
            return this.studentService.deleteStudentWithStudentNo(studentNo);
    }

    @DeleteMapping(path = "/withname/{name}/{surName}/{studentNo}")
    public Result deleteWithName(@PathVariable("name") String name,@PathVariable("surName") String surName,@PathVariable("studentNo") String studentNo)
    {
        return this.studentService.deleteStudent(name,surName,studentNo);
    }

    @PutMapping (path = "updatestudent/{studentNo}")
    public Result updateStudent(@PathVariable String studentNo,@RequestBody Student student)
    {
        return studentService.editStudent(studentNo,student);
    }

    @GetMapping("/findstudent/{studentNo}")
    public Student findStudentByStudentNo(@PathVariable String studentNo)
    {
        return studentService.findStudentByStudentNo(studentNo);
    }



}
