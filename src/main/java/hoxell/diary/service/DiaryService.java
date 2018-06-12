package hoxell.diary.service;

import hoxell.diary.database.repositories.*;
import hoxell.diary.model.Diary;
import hoxell.diary.model.components.Component;
import hoxell.diary.model.element.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    ElementRepository elementRepository;
    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    GroupRepository groupRepository;

    public List<Component> getAllComponents() {

        List<Component> components = componentRepository.findAll();

        for (Component component : components) {
            System.out.println("component = " + component);
            List<Element> elements = component.getElements();
            for (Element element : elements) {
                System.out.println("    element = " + element.getTitle());
            }
        }

        return components;

    }

    public List<Element> getAllElements() {
        return elementRepository.findAll();
    }

    public Component addElementsToComponent(Component old, List<Element> elements) {
        Component component = componentRepository.getOne(old.getId());
        componentRepository.delete(component);
        for (Element element : elements) {
            element.setComponent(component);
            element.setSection(sectionRepository.getOne(2));
            element.setGroup(groupRepository.getOne(1));
        }
        component.setElements(elements);
        component.setName(old.getName());
        component.setAuthorityLevel(old.getAuthorityLevel());
        component.setDiary(old.getDiary());
        component.setVisible(old.isVisible());

        return componentRepository.save(component);
    }

    public Diary saveDiary(Diary diary) {
        return diaryRepository.save(diary);

    }


}
