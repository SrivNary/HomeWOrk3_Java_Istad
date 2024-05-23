package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer id;
    private String title;
    private List<String> instructorNames;
    private List<String> instructorRequirements;
    private Date startDate;

    public Course(String title, List<String> instructorNames, List<String> instructorRequirements, Date startDate) {
    }

    public void add(Course course) {
    }

    public void save(Course course) {
    }
}