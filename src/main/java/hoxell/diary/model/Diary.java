package hoxell.diary.model;

import hoxell.diary.model.components.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "diaries")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int diary_id;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    private List<Component> components;

    public int getId() {
        return diary_id;
    }

    public void setId(int id) {
        this.diary_id = id;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
