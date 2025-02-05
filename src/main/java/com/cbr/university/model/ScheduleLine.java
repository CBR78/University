package com.cbr.university.model;

import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.LessonPairEnum;
import com.cbr.university.validation.group.Cascade;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.groups.ConvertGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleLine {
    @Id
    @GeneratedValue
    @Null(groups = {Create.class}, message = "Request must not include a ScheduleLine id.")
    @NotNull(groups = {Cascade.class,
            Update.class}, message = "Request must include a ScheduleLine id.")
    @IdExistsInDb(groups = {Cascade.class,
            Update.class}, typeObject = "ScheduleLine", message = "This ScheduleLine id is not in the database.")
    private Integer id;

    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a ScheduleLine date.")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @NotNull(groups = {Create.class,
            Update.class}, message = "Request must include a ScheduleLine lessonPair.")
    @LessonPairEnum(groups = {Create.class,
            Update.class}, message = "ScheduleLine typeObject is invalid. The parameter accepts only 1 of 4 values - FIRST_PAIR, SECOND_PAIR, THIRD_PAIR, FOURTH_PAIR.")
    private LessonPair lessonPair;

    @OneToOne
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Group id.")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Cascade.class),
            @ConvertGroup(from = Update.class, to = Cascade.class)
    })
    private Group group;

    @OneToOne
    @NotNull(groups = {Create.class, Update.class}, message = "Request must include a Teacher id.")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Cascade.class),
            @ConvertGroup(from = Update.class, to = Cascade.class)
    })
    private Teacher teacher;

    @OneToOne
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
