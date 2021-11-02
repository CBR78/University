package com.cbr.university.dto;

import com.cbr.university.model.LessonPair;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.LessonPairEnum;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.ConvertGroup;
import java.time.LocalDate;
import java.util.Objects;

public class ScheduleLineDto {
    @Null(groups = {Create.class}, message = "Request must not include a ScheduleLine id.")
    @NotNull(groups = {Cascade.class,
            Update.class}, message = "Request must include a ScheduleLine id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "ScheduleLine", message = "This ScheduleLine id is not in the database.")
    private Integer id;

    @NotNull(groups = {Create.class,
            Update.class}, message = "Request must include a ScheduleLine date.")
    private LocalDate date;

    @NotNull(groups = {Create.class,
            Update.class}, message = "Request must include a ScheduleLine lessonPair.")
    @LessonPairEnum(groups = {Create.class,
            Update.class}, message = "ScheduleLine typeObject is invalid. The parameter accepts only 1 of 4 values - FIRST_PAIR, SECOND_PAIR, THIRD_PAIR, FOURTH_PAIR.")
    private LessonPair lessonPair;

    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Group id.")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Cascade.class),
            @ConvertGroup(from = Update.class, to = Cascade.class)
    })
    private GroupDto group;

    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Teacher id.")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Cascade.class),
            @ConvertGroup(from = Update.class, to = Cascade.class)
    })
    private TeacherDto teacher;

    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Room id.")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Cascade.class),
            @ConvertGroup(from = Update.class, to = Cascade.class)
    })
    private RoomDto room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LessonPair getLessonPair() {
        return lessonPair;
    }

    public void setLessonPair(LessonPair lessonPair) {
        this.lessonPair = lessonPair;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, group, id, lessonPair, room, teacher);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScheduleLineDto other = (ScheduleLineDto) obj;
        return Objects.equals(date, other.date) && Objects.equals(group, other.group)
                && Objects.equals(id, other.id) && lessonPair == other.lessonPair
                && Objects.equals(room, other.room) && Objects.equals(teacher, other.teacher);
    }

    @Override
    public String toString() {
        return "ScheduleLineDtoRest [id=" + id + ", date=" + date + ", lessonPair=" + lessonPair
                + ", group=" + group + ", teacher=" + teacher + ", room=" + room + "]";
    }
}
