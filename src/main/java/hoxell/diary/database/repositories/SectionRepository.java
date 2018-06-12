package hoxell.diary.database.repositories;

import hoxell.diary.model.components.Component;
import hoxell.diary.model.element.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
}
