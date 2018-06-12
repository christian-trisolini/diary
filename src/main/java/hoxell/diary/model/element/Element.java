package hoxell.diary.model.element;

import hoxell.diary.model.components.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="elements")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int element_id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="component_id")
    private Component component;

    @ManyToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name = "section_id")
    private Section section;

    private Date date;
    private String title;
    private String text;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getId() {
        return element_id;
    }

    public void setId(int id) {
        this.element_id = id;
    }
}
