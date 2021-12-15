package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Create by liguo on 2021/12/8
 **/
@Data
public class Student implements Serializable {
    /**
     * 学号
     */
    private Integer id;

    private String name;

    private Integer age;

    private String className;

    /*public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", className='").append(className).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
