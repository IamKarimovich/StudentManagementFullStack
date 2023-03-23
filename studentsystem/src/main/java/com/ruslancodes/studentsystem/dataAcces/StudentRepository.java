package com.ruslancodes.studentsystem.dataAcces;

import com.ruslancodes.studentsystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Transactional
    @Modifying
    @Query("delete from Student s where s.name = ?1 and s.surName = ?2 and s.studentNo = ?3")
    void deleteStudentByNameAndSurNameAndStudentNo(String name, String surName, String studentNo);

    @Query("select (count(s) > 0) from Student s where s.name = ?1 and s.surName = ?2 and s.studentNo = ?3")
    boolean existsStudentByNameAndSurNameAndStudentNo(String name, String surName, String studentNo);

    @Query("select s from Student s where s.studentNo = ?1")
    Student findStudentByStudentNo(String studentNo);

    @Query("select (count(s) > 0) from Student s where s.studentNo = ?1")
    boolean existsStudentByStudentNo(String studentNo);

    @Transactional
    @Modifying
    @Query("delete from Student s where s.studentNo = ?1")
    void deleteStudentByStudentNo(String StudentNo);

    @Query("select count(s) from Student s where s.studentNo = ?1")
    long countStudentByStudentNo(String studentNo);


}
