package com.cbr.university.model;

import java.time.LocalDate;
import java.util.Objects;

public class ScheduleLine {
    private int id;
    private LocalDate date;
    private LessonPairNumber lessonPairNumber;
    private Group group;
    private Teacher teacher;
    private Course сourse;
    private Room room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LessonPairNumber getLessonPairNumber() {
        return lessonPairNumber;
    }

    public void setLessonPairNumber(LessonPairNumber lessonPairNumber) {
        this.lessonPairNumber = lessonPairNumber;
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

    public Course getСourse() {
        return сourse;
    }

    public void setСourse(Course сourse) {
        this.сourse = сourse;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, group, id, lessonPairNumber, room, teacher, сourse);
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
        return Objects.equals(date, other.date) && Objects.equals(group, other.group) && id == other.id
                && lessonPairNumber == other.lessonPairNumber && Objects.equals(room, other.room)
                && Objects.equals(teacher, other.teacher) && Objects.equals(сourse, other.сourse);
    }
}
