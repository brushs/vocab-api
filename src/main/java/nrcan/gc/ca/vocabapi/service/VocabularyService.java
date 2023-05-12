package nrcan.gc.ca.vocabapi.service;

import nrcan.gc.ca.vocabapi.model.entity.Vocabulary;
import nrcan.gc.ca.vocabapi.model.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyService {

    @Autowired
    private VocabularyRepository vocabularyRepository;

    public List<Vocabulary> getVocabularies() {
        return vocabularyRepository.findAll();
    }
}
