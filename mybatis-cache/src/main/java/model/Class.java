package model;

import lombok.Data;

/**
 * Create by liguo on 2021/12/8
 **/
@Data
public class Class {
    private int classId;

    private String className;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Class{");
        sb.append("classId=").append(classId);
        sb.append(", className='").append(className).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
