package com.cbr.university.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.ConvertGroup;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.LessonPairEnum;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;

@Entity
@Table(name = "schedule_lines")
public class ScheduleLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_line_id")
    @Null(groups = { Create.class }, message = "Request must not include a ScheduleLine id.")
    @NotNull(groups = { Cascade.class,
            Update.class }, message = "Request must include a ScheduleLine id.")
    @IdExistsInDb(groups = { Cascade.class,
            Update.class }, typeObject = "ScheduleLine", message = "This ScheduleLine id is not in the database.")
    private Integer id;

    @Column(name = "schedule_line_date")
    @NotNull(groups = { Create.class,
            Update.class }, message = "Request must include a ScheduleLine date.")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_pair")
    @NotNull(groups = { Create.class,
            Update.class }, message = "Request must include a ScheduleLine lessonPair.")
    @LessonPairEnum(groups = { Create.class,
            Update.class }, message = "ScheduleLine typeObject is invalid. The parameter accepts only 1 of 4 values - FIRST_PAIR, SECOND_PAIR, THIRD_PAIR, FOURTH_PAIR.")
    private LessonPair lessonPair;

    @OneToOne
    @JoinColumn(name = "group_id")
    @NotNull(groups = { Create.class, Update.class }, message = "Request must include a Group id.")
    @Valid
    @ConvertGroup(from = Create.class, to = Cascade.class)
    @ConvertGroup(from = Update.class, to = Cascade.class)
    private Group group;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    @NotNull(groups = { Create.class, Update.class }, message = "Request must include a Teacher id.")
    @Valid
    @ConvertGroup(from = Create.class, to = Cascade.class)
    @ConvertGroup(from = Update.class, to = Cascade.class)
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "room_id")
    @NotNull(groups = { Create.class, Update.class }, message = "Request must include a Room id.")
    @Valid
    @ConvertGroup(from = Create.class, to = Cascade.class)
    @ConvertGroup(from = Update.class, to = Cascade.class)
    private Room room;

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
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
        ScheduleLine other = (ScheduleLine) obj;
        return Objects.equals(date, other.date) && Objects.equals(group, other.group)
                && Objects.equals(id, other.id) && lessonPair == other.lessonPair
                && Objects.equals(room, other.room) && Objects.equals(teacher, other.teacher);
    }

    @Override
    public String toString() {
        return "ScheduleLine [id=" + id + ", date=" + date + ", lessonPair=" + lessonPair + ", group="
                + group + ", teacher=" + teacher + ", room=" + room + "]";
    }
}
