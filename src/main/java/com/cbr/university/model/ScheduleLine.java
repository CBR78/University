package com.cbr.university.model;

import java.time.LocalDate;
import java.util.Objects;

public class ScheduleLine {
    private int id;
    private LocalDate date;
    private LessonPair lessonPair;
    private int groupId;
    private int teacherId;
    private int courseId;
    private int roomId;

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

    public LessonPair getLessonPair() {
        return lessonPair;
    }

    public void setLessonPair(LessonPair lessonPair) {
        this.lessonPair = lessonPair;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, date, groupId, id, lessonPair, roomId, teacherId);
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
        return courseId == other.courseId && Objects.equals(date, other.date) && groupId == other.groupId
                && id == other.id && lessonPair == other.lessonPair && roomId == other.roomId
                && teacherId == other.teacherId;
    }

    @Override
    public String toString() {
        return "ScheduleLine [id=" + id + ", date=" + date + ", lessonPair=" + lessonPair + ", groupId="
                + groupId + ", teacherId=" + teacherId + ", courseId=" + courseId + ", roomId=" + roomId
                + "]";
    }
}
