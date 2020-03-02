package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor

public class Course {
    Long id;
    String name;
    List<Lesson> lessonList;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lessonList=" + lessonList +
                '}';
    }

    public Course() {
        lessonList = new ArrayList<>();
    }

    public Course(String name) {
        this.name = name;
        lessonList = new ArrayList<>();
    }
}