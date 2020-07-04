package com.cbr.university.model.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.RequestUI;
import com.cbr.university.validation.group.Update;

public class GroupDtoRest {
    @Null(groups = { Create.class }, message = "Request must not include a Group id.")
    @NotNull(groups = { Cascade.class, Update.class }, message = "Request must include a Group id.")
    @IdExistsInDb(groups = { Cascade.class,
            Update.class }, typeObject = "Group", message = "This Group id is not in the database.")
    private Integer id;

    @Null(groups = { Cascade.class }, message = "Request must not include a Group name.")
    @NotNull(groups = { Create.class, Update.class }, message = "Request must include a Group name.")
    @Size(groups = { Create.class, Update.class,
            RequestUI.class }, min = 2, max = 20, message = "Group name should contain from {min} to {max} letters.")
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
        GroupDtoRest other = (GroupDtoRest) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "GroupDtoRest [id=" + id + ", name=" + name + "]";
    }
}
