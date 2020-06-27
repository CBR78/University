package com.cbr.university.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.None;
import com.cbr.university.validation.group.Update;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    @Null(groups = { Create.class }, message = "Request must not include a Teacher id")
    @NotNull(groups = { Cascade.class, Update.class }, message = "Request must include a Teacher id")
    @IdExistsInDb(groups = { Cascade.class,
            Update.class }, typeObject = "Teacher", message = "This Teacher id is not in the database")
    private Integer id;

    @Column(name = "teacher_first_name")
    @Null(groups = { Cascade.class }, message = "Request must not include a Teacher firstName")
    @NotNull(groups = { Create.class,
            Update.class }, message = "Request must include a Teacher firstName")
    @Size(groups = { Create.class,
            Update.class }, min = 2, max = 50, message = "Teacher firstName should contain from {min} to {max} letters")
    private String firstName;

    @Column(name = "teacher_last_name")
    @Null(groups = { Cascade.class }, message = "Request must not include a Teacher lastName")
    @NotNull(groups = { Create.class,
            Update.class }, message = "Request must include a Teacher lastName")
    @Size(groups = { Create.class,
            Update.class }, min = 2, max = 50, message = "Teacher lastName should contain from {min} to {max} letters")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "course_id")
    @NotNull(groups = { Create.class, Update.class }, message = "Request must include a Course id")
    @Valid
    @ConvertGroup(from = Cascade.class, to = None.class)
    @ConvertGroup(from = Create.class, to = Cascade.class)
    @ConvertGroup(from = Update.class, to = Cascade.class)
    private Course course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
