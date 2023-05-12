package nrcan.gc.ca.vocabapi.controller.graphql;

import nrcan.gc.ca.vocabapi.model.entity.Term;
import nrcan.gc.ca.vocabapi.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TermController {

    @Autowired
    private TermService termService;

    @QueryMapping
    public List<Term> getTerms(@Argument int count, @Argument int offset) {
        return termService.getTerms();
    }

    @QueryMapping
    public String getTermTranslation(@Argument String termName) {
        return termService.getTermTranslation(termName);
    }
}
