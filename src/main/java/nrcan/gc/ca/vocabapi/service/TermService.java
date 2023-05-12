package nrcan.gc.ca.vocabapi.service;

import jakarta.persistence.EntityManager;
import nrcan.gc.ca.vocabapi.model.entity.QTerm;
import nrcan.gc.ca.vocabapi.model.entity.Term;
import nrcan.gc.ca.vocabapi.model.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

@Service
public class TermService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TermRepository termRepository;

    public List<Term> getTerms() {
        return termRepository.findAll();
    }

    public String getTermTranslation(String termName) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QTerm term = QTerm.term;

        List<Term> translation = queryFactory.selectFrom(term)
                                    .where(term.nameEn.eq(termName))
                                    .limit(1)
                                    .fetch();

        if (translation.size() == 1) {
            return translation.get(0).getNameFr();
        } else {
            translation = queryFactory.selectFrom(term)
                    .where(term.nameFr.eq(termName))
                    .limit(1)
                    .fetch();

            if (translation.size() == 1) {
                return translation.get(0).getNameEn();
            }
        }

        return "";
    }
}
