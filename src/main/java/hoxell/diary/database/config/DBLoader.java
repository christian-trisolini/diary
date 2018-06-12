package hoxell.diary.database.config;


import hoxell.diary.database.repositories.*;
import hoxell.diary.model.components.Component;
import hoxell.diary.model.element.Element;
import hoxell.diary.model.element.Group;
import hoxell.diary.model.element.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class DBLoader {

    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    ElementRepository elementRepository;
    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SectionRepository sectionRepository;



    public DBLoader() {
    }

    @PostConstruct
    public void load() {
//        if(groupRepository.findAll().isEmpty()){
//            Group group = new Group();
//            group.setName("direzione");
//            groupRepository.save(group);
//        }
//        if(sectionRepository.findAll().isEmpty()){
//            Section section = new Section();
//            section.setName("news");
//            sectionRepository.save(section);
//        }
//        if(elementRepository.findAll().isEmpty()){
//            for (int i = 0; i < 3; i++) {
//                Element element = new Element();
//                element.setTitle("Element " + i);
//                element.setDate(new Date());
//                element.setText("testo " + i);
//                elementRepository.save(element);
//            }
//        }
//        if(componentRepository.findAll().isEmpty()){
//            Component component = new Component();
//            component.setAuthorityLevel(1);
//            component.setName("info");
//            component.setVisible(true);
//            componentRepository.save(component);
//        }
    }

}
