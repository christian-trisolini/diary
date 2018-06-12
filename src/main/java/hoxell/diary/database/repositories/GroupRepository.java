package hoxell.diary.database.repositories;

import hoxell.diary.model.element.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository  extends JpaRepository<Group, Integer> {


}
