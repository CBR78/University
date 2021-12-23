package com.cbr.university.model;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.RequestUI;
import com.cbr.university.validation.group.Update;

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
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    @Null(groups = {Create.class}, message = "Request must not include a Student id.")
    @NotNull(groups = {Cascade.class, Update.class}, message = "Request must include a Student id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "Student", message = "This Student id is not in the database.")
    private int id;

    @Column(name = "student_first_name")
    @Null(groups = {Cascade.class}, message = "Request must not include a Student firstName.")
    @NotNull(groups = {Create.class,
            Update.class}, message = "Request must include a Student firstName.")
    @Size(groups = {Create.class, Update.class,
            RequestUI.class}, min = 2, max = 50, message = "Student firstName should contain from {min} to {max} letters.")
    private String firstName;

    @Column(name = "student_last_name")
    @Null(groups = {Cascade.class}, message = "Request must not include a Student lastName.")
    @NotNull(groups = {Create.class,
            Update.class}, message = "Request must include a Student lastName.")
    @Size(groups = {Create.class, Update.class,
            RequestUI.class}, min = 2, max = 50, message = "Student lastName should contain from {min} to {max} letters.")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "group_id")
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Group id.")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Cascade.class),
            @ConvertGroup(from = Update.class, to = Cascade.class)
    })
    private Group group;

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
