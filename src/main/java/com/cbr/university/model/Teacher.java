package com.cbr.university.model;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.None;
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
public class Teacher {
    @Id
    @GeneratedValue
    @Null(groups = {Create.class}, message = "Request must not include a Teacher id.")
    @NotNull(groups = {Cascade.class, Update.class}, message = "Request must include a Teacher id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "Teacher", message = "This Teacher id is not in the database.")
    private Integer id;

    @Null(groups = {Cascade.class}, message = "Request must not include a Teacher firstName.")
    @NotNull(groups = {Create.class, Update.class,
            RequestUI.class}, message = "Request must include a Teacher firstName.")
    @Size(groups = {Create.class, Update.class,
            RequestUI.class}, min = 2, max = 50, message = "Teacher firstName should contain from {min} to {max} letters.")
    private String firstName;

    @Null(groups = {Cascade.class}, message = "Request must not include a Teacher lastName.")
    @NotNull(groups = {Create.class, Update.class,
            RequestUI.class}, message = "Request must include a Teacher lastName.")
    @Size(groups = {Create.class, Update.class,
            RequestUI.class}, min = 2, max = 50, message = "Teacher lastName should contain from {min} to {max} letters.")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "course_id")
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Course id.")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Cascade.class, to = None.class),
            @ConvertGroup(from = Create.class, to = Cascade.class),
            @ConvertGroup(from = Update.class, to = Cascade.class)
    })
    private Course course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Teacher teacher = (Teacher) o;
        return id != null && Objects.equals(id, teacher.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
