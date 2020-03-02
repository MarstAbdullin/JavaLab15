package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    Long id;
    String name;
    Course course;


    public String getName() {
        return name;
    }

    public Lesson(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", course=" + course.getName() +
                '}';
    }
}
