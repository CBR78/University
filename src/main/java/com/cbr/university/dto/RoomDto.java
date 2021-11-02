package com.cbr.university.dto;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.RequestUI;
import com.cbr.university.validation.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Objects;

public class RoomDto {
    @Null(groups = {Create.class}, message = "Request must not include a Room id.")
    @NotNull(groups = {Cascade.class, Update.class}, message = "Request must include a Room id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "Room", message = "This Room id is not in the database.")
    private Integer id;

    @Null(groups = {Cascade.class}, message = "Request must not include a Room name.")
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Room name.")
    @Size(groups = {Create.class, Update.class,
            RequestUI.class}, min = 2, max = 20, message = "Room name should contain from {min} to {max} letters.")
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
        RoomDto other = (RoomDto) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "RoomDtoRest [id=" + id + ", name=" + name + "]";
    }
}
