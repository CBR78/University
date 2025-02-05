package com.cbr.university.model;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.RequestUI;
import com.cbr.university.validation.group.Update;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id
    @GeneratedValue
    @Null(groups = {Create.class}, message = "Request must not include a Student id.")
    @NotNull(groups = {Cascade.class, Update.class}, message = "Request must include a Student id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "Student", message = "This Student id is not in the database.")
    private Integer id;

    @Null(groups = {Cascade.class}, message = "Request must not include a Student firstName.")
    @NotNull(groups = {Create.class,
            Update.class}, message = "Request must include a Student firstName.")
    @Size(groups = {Create.class, Update.class,
            RequestUI.class}, min = 2, max = 50, message = "Student firstName should contain from {min} to {max} letters.")
    private String firstName;

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
