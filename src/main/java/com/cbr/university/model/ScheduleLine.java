package com.cbr.university.model;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.LessonPairEnum;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

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
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "schedule_lines")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_line_id")
    @Null(groups = {Create.class}, message = "Request must not include a ScheduleLine id.")
    @NotNull(groups = {Cascade.class,
            Update.class}, message = "Request must include a ScheduleLine id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "ScheduleLine", message = "This ScheduleLine id is not in the database.")
    private Integer id;

    @Column(name = "schedule_line_date")
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a ScheduleLine date.")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_pair")
    @NotNull(groups = {Create.class,
            Update.class}, message = "Request must include a ScheduleLine lessonPair.")
    @LessonPairEnum(groups = {Create.class,
            Update.class}, message = "ScheduleLine typeObject is invalid. The parameter accepts only 1 of 4 values - FIRST_PAIR, SECOND_PAIR, THIRD_PAIR, FOURTH_PAIR.")
    private LessonPair lessonPair;

    @OneToOne
    @JoinColumn(name = "group_id")
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Group id.")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Cascade.class),
            @ConvertGroup(from = Update.class, to = Cascade.class)
    })
    private Group group;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Teacher id.")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Cascade.class),
            @ConvertGroup(from = Update.class, to = Cascade.class)
    })
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "room_id")
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Room id.")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Cascade.class),
            @ConvertGroup(from = Update.class, to = Cascade.class)
    })
    private Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ScheduleLine that = (ScheduleLine) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
