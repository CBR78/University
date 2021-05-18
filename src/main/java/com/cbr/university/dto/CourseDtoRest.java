package com.cbr.university.dto;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CourseDtoRest {
    @Null(groups = { Create.class, None.class }, message = "Request must not include a Course id.")
    @NotNull(groups = { Cascade.class, Update.class }, message = "Request must include a Course id.")
    @IdExistsInDb(groups = { Cascade.class,
            Update.class }, typeObject = "Course", message = "This Course id is not in the database.")
    private Integer id;

    @Null(groups = { Cascade.class, None.class }, message = "Request must not include a Course name.")
    @NotNull(groups = { Create.class, Update.class }, message = "Request must include a Course name.")
    @Size(groups = { Create.class, Update.class,
            RequestUI.class }, min = 2, max = 30, message = "Course name should contain from {min} to {max} letters.")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        CourseDtoRest other = (CourseDtoRest) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "CourseDtoRest [id=" + id + ", name=" + name + "]";
    }
}
