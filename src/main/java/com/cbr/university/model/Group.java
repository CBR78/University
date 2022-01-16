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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    @Null(groups = {Create.class}, message = "Request must not include a Group id.")
    @NotNull(groups = {Cascade.class, Update.class}, message = "Request must include a Group id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "Group", message = "This Group id is not in the database.")
    private int id;

    @Column(name = "group_name")
    @Null(groups = {Cascade.class}, message = "Request must not include a Group name.")
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Group name.")
    @Size(groups = {Create.class, Update.class,
            RequestUI.class}, min = 2, max = 20, message = "Group name should contain from {min} to {max} letters.")
    private String name;

    public Group(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Group() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", name=" + name + "]";
    }
}
