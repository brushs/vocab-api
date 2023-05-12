package nrcan.gc.ca.vocabapi.model.repository;

import nrcan.gc.ca.vocabapi.model.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Integer> {
}
