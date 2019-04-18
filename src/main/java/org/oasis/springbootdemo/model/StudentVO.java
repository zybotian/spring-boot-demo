package org.oasis.springbootdemo.model;

/**
 * @author tianbo
 * @date 2019-04-18
 */
public class StudentVO {
    private Integer id;
    private String name;

    public StudentVO() {
    }

    public StudentVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
