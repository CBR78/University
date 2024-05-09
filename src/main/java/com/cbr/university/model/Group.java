package com.cbr.university.model;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.RequestUI;
import com.cbr.university.validation.group.Update;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    @Null(groups = {Create.class}, message = "Request must not include a Group id.")
    @NotNull(groups = {Cascade.class, Update.class}, message = "Request must include a Group id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "Group", message = "This Group id is not in the database.")
    private Integer id;

    @Column(name = "group_name")
    @Null(groups = {Cascade.class}, message = "Request must not include a Group name.")
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Group name.")
    @Size(groups = {Create.class, Update.class,
            RequestUI.class}, min = 2, max = 20, message = "Group name should contain from {min} to {max} letters.")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Group group = (Group) o;
        return id != null && Objects.equals(id, group.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
