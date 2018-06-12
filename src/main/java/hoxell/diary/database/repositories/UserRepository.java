package hoxell.diary.database.repositories;

import hoxell.diary.model.HoxUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<HoxUser, Integer> {

    HoxUser findByUsername(String username);
}
