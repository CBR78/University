package com.cbr.university.model;

import com.cbr.university.dto.TeacherDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int id;

    @Column(name = "teacher_first_name")
    private String firstName;

    @Column(name = "teacher_last_name")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Teacher() {
    }

    public Teacher(int id) {
        this.id = id;
    }

    public Teacher(TeacherDto teacherDto) {
        if (teacherDto.getId() != null) {
            this.id = teacherDto.getId();
        }
        this.firstName = teacherDto.getFirstName();
        this.lastName = teacherDto.getLastName();
        this.course = new Course(teacherDto.getCourse().getId());
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, firstName, id, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teacher other = (Teacher) obj;
        return Objects.equals(course, other.course) && Objects.equals(firstName, other.firstName)
                && Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName);
    }

    @Override
    public String toString() {
        return "Teacher [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", course="
                + course + "]";
    }
}
