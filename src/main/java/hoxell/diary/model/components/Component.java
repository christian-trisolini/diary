package hoxell.diary.model.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hoxell.diary.model.Diary;
import hoxell.diary.model.element.Element;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int component_id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL)
    private List<Element> elements;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="diary_id")
    private Diary diary;
    private boolean visible;
    private int authorityLevel;

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getAuthorityLevel() {
        return authorityLevel;
    }

    public void setAuthorityLevel(int authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    public int getId() {
        return component_id;
    }

    public void setId(int id) {
        this.component_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }
}
