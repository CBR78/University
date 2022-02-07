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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    @Null(groups = {Create.class, None.class}, message = "Request must not include a Course id.")
    @NotNull(groups = {Cascade.class, Update.class}, message = "Request must include a Course id.")
    @IdExistsInDb(groups = {Cascade.class, Update.class},
            typeObject = "Course", message = "This Course id is not in the database.")
    private Integer id;

    @Column(name = "course_name")
    @Null(groups = {Cascade.class, None.class}, message = "Request must not include a Course name.")
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Course name.")
    @Size(groups = {Create.class, Update.class, RequestUI.class},
            min = 2, max = 30, message = "Course name should contain from {min} to {max} letters.")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return id != null && Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
