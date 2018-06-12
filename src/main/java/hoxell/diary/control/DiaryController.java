package hoxell.diary.control;

import hoxell.diary.model.Diary;
import hoxell.diary.service.DiaryService;
import hoxell.diary.model.components.Component;
import hoxell.diary.model.element.Element;
import hoxell.diary.multiTenancy.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Controller
public class DiaryController {

    @Autowired
    private DiaryService service;


    @GetMapping("/manage/diary_spring")
    public String diary(Model model) {

        List<Element> elements = service.getAllElements();
        List<Component> components = service.getAllComponents();
        if (components.isEmpty()) return "diary";
        service.addElementsToComponent(components.get(0), elements);
        model.addAttribute("components", components);

        return "diary";
    }

    @PostMapping("/manage/diary_spring")
    public ResponseEntity<?> createSample(@RequestHeader("X-TenantID") String tenantName) {

        Diary diary = new Diary();
        service.saveDiary(diary);
        return ResponseEntity.ok(diary);
    }

    @GetMapping("/manage/diary_spring/edit/{id}")
    public String editElement(Model model, @PathVariable("id") int id) {


        return "edit";
    }

}
