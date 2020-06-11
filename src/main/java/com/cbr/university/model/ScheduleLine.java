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

@Entity
@Table(name = "schedule_lines")
public class ScheduleLine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_line_id")
    private int id;
    
    @Column(name = "schedule_line_date")
    private LocalDate date;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_pair")
    private LessonPair lessonPair;
    
    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;
    
    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
        
    @OneToOne
    @JoinColumn(name = "room_id")
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
        return Objects.equals(date, other.date) && Objects.equals(group, other.group) && id == other.id
                && lessonPair == other.lessonPair && Objects.equals(room, other.room)
                && Objects.equals(teacher, other.teacher);
    }

    @Override
    public String toString() {
        return "ScheduleLine [id=" + id + ", date=" + date + ", lessonPair=" + lessonPair + ", group="
                + group + ", teacher=" + teacher + ", room=" + room + "]";
    }
}
