package com.example.productcatalogue.data.model

data class Dept(
    val id: String = "",
    val name: String = ""
)

data class Student(
    val id: String = "",
    val name: String = "",
    val age: String = ""
)

data class DeptWithStudent(
    val dept: Dept = Dept("", ""),
    val students: List<Student> = listOf(Student(), Student())
//    val student: Student = Student("", "", "")
)
