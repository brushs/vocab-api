package nrcan.gc.ca.vocabapi.service;

import nrcan.gc.ca.vocabapi.model.entity.Term;
import nrcan.gc.ca.vocabapi.model.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermService {

    @Autowired
    private TermRepository termRepository;

    public List<Term> getTerms() {
        return termRepository.findAll();
    }
}
