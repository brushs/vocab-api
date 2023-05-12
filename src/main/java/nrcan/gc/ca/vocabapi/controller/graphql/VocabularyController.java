package nrcan.gc.ca.vocabapi.controller.graphql;

import nrcan.gc.ca.vocabapi.model.entity.Vocabulary;
import nrcan.gc.ca.vocabapi.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VocabularyController {

    @Autowired
    private VocabularyService vocabularyService;

    @QueryMapping
    public List<Vocabulary> getVocabularies(@Argument int count, @Argument int offset) {
        return vocabularyService.getVocabularies();
    }
}
