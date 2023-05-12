package nrcan.gc.ca.vocabapi.model.repository;

import nrcan.gc.ca.vocabapi.model.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Integer> {
}
