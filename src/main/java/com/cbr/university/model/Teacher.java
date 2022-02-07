package com.cbr.university.model;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.None;
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
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    @Null(groups = {Create.class}, message = "Request must not include a Teacher id.")
    @NotNull(groups = {Cascade.class, Update.class}, message = "Request must include a Teacher id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "Teacher", message = "This Teacher id is not in the database.")
    private Integer id;

    @Column(name = "teacher_first_name")
    @Null(groups = {Cascade.class}, message = "Request must not include a Teacher firstName.")
    @NotNull(groups = {Create.class, Update.class,
            RequestUI.class}, message = "Request must include a Teacher firstName.")
    @Size(groups = {Create.class, Update.class,
            RequestUI.class}, min = 2, max = 50, message = "Teacher firstName should contain from {min} to {max} letters.")
    private String firstName;

    @Column(name = "teacher_last_name")
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
