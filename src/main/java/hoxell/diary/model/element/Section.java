package hoxell.diary.model.element;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int section_id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Element> elements;

    public int getId() {
        return section_id;
    }

    public void setId(int id) {
        this.section_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
