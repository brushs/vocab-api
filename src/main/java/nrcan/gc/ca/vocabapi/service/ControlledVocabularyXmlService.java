package nrcan.gc.ca.vocabapi.service;

import java.util.List;

import nrcan.gc.ca.vocabapi.model.entity.Term;
import nrcan.gc.ca.vocabapi.model.xml.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlledVocabularyXmlService {

    @Autowired
    TermService termService;

    public Node getVocabularyTree(String vocabularyName, List<String> languages) {

        Node root = new Node();
        root.setId(vocabularyName);
        root.setLabel(vocabularyName);
        root.setIsComposedBy(new Node.IsComposedBy());

        List<Term> rootTerms = termService.getRootTerms(vocabularyName);

        for (Term t : rootTerms) {
            Node node = new Node();
            node.setId(String.valueOf(t.getId()));
            node.setLabel(languages.contains("en") ? t.getNameEn() : t.getNameFr());

            node.setIsComposedBy(getChildTree(node, languages));

            root.getIsComposedBy().getNode().add(node);

            if (languages.size() == 2) {
                Node bilingualNode = new Node();
                bilingualNode.setId(String.valueOf(t.getId() + "fr"));
                bilingualNode.setLabel(t.getNameFr());

                root.getIsComposedBy().getNode().add(bilingualNode);
            }
        }

        return root;
    }

    private Node.IsComposedBy getChildTree(Node parent, List<String> languages) {
        List<Term> childTerms = termService.getChildTerms(Integer.parseInt(parent.getId()));

        Node.IsComposedBy children = new Node.IsComposedBy();

        for (Term t : childTerms) {
            Node node = new Node();
            node.setId(String.valueOf(t.getId()));
            node.setLabel(languages.contains("en") ? t.getNameEn() : t.getNameFr());

            node.setIsComposedBy(getChildTree(node, languages));

            children.getNode().add(node);

            if (languages.size() == 2) {
                Node bilingualNode = new Node();
                bilingualNode.setId(String.valueOf(t.getId() + "fr"));
                bilingualNode.setLabel(t.getNameFr());

                children.getNode().add(bilingualNode);
            }
        }

        return children;
    }
}
