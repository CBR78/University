package com.cbr.university.model;

import com.cbr.university.dto.StudentDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;

    @Column(name = "student_first_name")
    private String firstName;

    @Column(name = "student_last_name")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Student() {
    }

    public Student(StudentDto studentDto) {
        this.id = studentDto.getId();
        this.firstName = studentDto.getFirstName();
        this.lastName = studentDto.getLastName();
        this.group = new Group(studentDto.getGroup());  //-----------------------------------------
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, group, id, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return Objects.equals(firstName, other.firstName) && Objects.equals(group, other.group)
                && Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName);
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", group="
                + group + "]";
    }
}
