package com.cbr.university.model;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.RequestUI;
import com.cbr.university.validation.group.Update;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    @Null(groups = {Create.class}, message = "Request must not include a Student id.")
    @NotNull(groups = {Cascade.class, Update.class}, message = "Request must include a Student id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "Student", message = "This Student id is not in the database.")
    private Integer id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return id != null && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
