package nrcan.gc.ca.vocabapi.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import nrcan.gc.ca.vocabapi.model.entity.QTerm;
import nrcan.gc.ca.vocabapi.model.entity.QVocabulary;
import nrcan.gc.ca.vocabapi.model.entity.Term;
import nrcan.gc.ca.vocabapi.model.entity.Vocabulary;
import nrcan.gc.ca.vocabapi.model.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private VocabularyRepository vocabularyRepository;

    public List<Vocabulary> getVocabularies() {
        return vocabularyRepository.findAll();
    }

    public Vocabulary getVocabulary(String vocabularyName) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QVocabulary vocabulary = QVocabulary.vocabulary;

        List<Vocabulary> vocabularies = queryFactory.selectFrom(vocabulary)
                .where(vocabulary.nameEn.eq(vocabularyName).or(
                        vocabulary.nameFr.eq(vocabularyName)
                ))
                .limit(1)
                .fetch();

        return vocabularies.get(0);
    }
}
