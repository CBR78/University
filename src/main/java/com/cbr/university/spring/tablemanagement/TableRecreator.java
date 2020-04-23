package com.cbr.university.spring.tablemanagement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TableRecreator {

    private static final String SQL_DELETE_TABLES = "DROP TABLE IF EXISTS departments, groups, courses, students, teachers, rooms, lesson_pair_numbers, schedule_lines CASCADE";
    private static final String SQL_CREATE_TABLE_DEPARTMENTS = "CREATE TABLE departments (department_id serial PRIMARY KEY, department_name text)";
    private static final String SQL_CREATE_TABLE_GROUPS = "CREATE TABLE groups (group_id serial PRIMARY KEY, group_name text, department_id integer REFERENCES departments(department_id) ON DELETE CASCADE)";
    private static final String SQL_CREATE_TABLE_COURSES = "CREATE TABLE courses (course_id serial PRIMARY KEY, course_name text, department_id integer REFERENCES departments(department_id) ON DELETE CASCADE)";
    private static final String SQL_CREATE_TABLE_STUDENTS = "CREATE TABLE students (student_id serial PRIMARY KEY, student_first_name text, student_last_name text, group_id integer REFERENCES groups(group_id) ON DELETE CASCADE)";
    private static final String SQL_CREATE_TABLE_TEACHERS = "CREATE TABLE teachers (teacher_id serial PRIMARY KEY, teacher_first_name text, teacher_last_name text, course_id integer REFERENCES courses(course_id) ON DELETE CASCADE, department_id integer REFERENCES departments(department_id) ON DELETE CASCADE)";
    private static final String SQL_CREATE_TABLE_ROOMS = "CREATE TABLE rooms (room_id serial PRIMARY KEY, room_name text)";
    private static final String SQL_CREATE_TABLE_LESSON_PAIR_NUMBERS = "CREATE TABLE lesson_pair_numbers (lesson_pair_id serial PRIMARY KEY, lesson_pair_number text)";
    private static final String SQL_CREATE_TABLE_SCHEDULE_LINES = "CREATE TABLE schedule_lines (schedule_line_id serial PRIMARY KEY, schedule_line_date date, lesson_pair_id integer REFERENCES lesson_pair_numbers(lesson_pair_id) ON DELETE CASCADE, group_id integer REFERENCES groups(group_id) ON DELETE CASCADE, teacher_id integer REFERENCES teachers(teacher_id) ON DELETE CASCADE, course_id integer REFERENCES courses(course_id) ON DELETE CASCADE, room_id integer REFERENCES rooms(room_id) ON DELETE CASCADE)";
    JdbcTemplate jdbcTemplate;

    @Autowired
    public TableRecreator(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean deleteTables() {
        return jdbcTemplate.update(SQL_DELETE_TABLES) > 0;
    }

    public void createTables() {
        jdbcTemplate.execute(SQL_CREATE_TABLE_DEPARTMENTS);
        jdbcTemplate.execute(SQL_CREATE_TABLE_GROUPS);
        jdbcTemplate.execute(SQL_CREATE_TABLE_COURSES);
        jdbcTemplate.execute(SQL_CREATE_TABLE_STUDENTS);
        jdbcTemplate.execute(SQL_CREATE_TABLE_TEACHERS);
        jdbcTemplate.execute(SQL_CREATE_TABLE_ROOMS);
        jdbcTemplate.execute(SQL_CREATE_TABLE_LESSON_PAIR_NUMBERS);
        jdbcTemplate.execute(SQL_CREATE_TABLE_SCHEDULE_LINES);
    }
}
