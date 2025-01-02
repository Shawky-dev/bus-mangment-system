package com.habbypanda.bus_mangment_system.user.parent;

import com.habbypanda.bus_mangment_system.user.student.Student;

public class ParentDTO {
    private String name;
    private String email;
    private String password;
    private Integer StudentID;

    public Parent toParent(Student student) {
        return Parent.builder()
                .name(name)
                .email(email)
                .password(password)
                .student(student != null ? student : new Student())
                .build();
    }

}
