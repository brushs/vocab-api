package nrcan.gc.ca.vocabapi.controller.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import nrcan.gc.ca.vocabapi.model.xml.Node;
import nrcan.gc.ca.vocabapi.service.ControlledVocabularyXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VocabXmlController {

    @Autowired
    private ControlledVocabularyXmlService controlledVocabularyXmlService;

    @RequestMapping(value = "exportxml", method = RequestMethod.GET)
    public void exportXml(@RequestParam String vocab,
                          @RequestParam List<String> lang,
                          final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception
    {
        // TODO
        // Validate Export XML parameters

        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "attachment; filename=" + "temp" + ".xml");
        final Node node = controlledVocabularyXmlService.getVocabularyTree(vocab, lang);
        final JAXBContext jaxbContext = JAXBContext.newInstance(Node.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(node, response.getOutputStream());
    }

    @RequestMapping(value = "exportxmlsample", method = RequestMethod.GET)
    public void exportXmlSample(final HttpServletRequest request,
                                final HttpServletResponse response) throws Exception
    {
        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "attachment; filename=" + "temp" + ".xml");
        final Node node = getSampleNode();
        final JAXBContext jaxbContext = JAXBContext.newInstance(Node.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(node, response.getOutputStream());

    }

    private Node getSampleNode() {
        Node root = new Node();
        root.setId("1234");
        root.setLabel("TestRoot");

        Node.IsComposedBy children = new Node.IsComposedBy();
        root.setIsComposedBy(children);

        Node child1 = new Node();
        child1.setId("2345");
        child1.setLabel("Child1");
        children.getNode().add(child1);

        Node child2 = new Node();
        child2.setId("3456");
        child2.setLabel("Child2");
        children.getNode().add(child2);

        return root;
    }
}

