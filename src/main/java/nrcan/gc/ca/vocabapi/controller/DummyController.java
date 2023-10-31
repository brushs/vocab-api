package nrcan.gc.ca.vocabapi.controller;

import gov.nih.nlm.ncbi.jats1.Abstract;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.apache.commons.lang3.time.DateUtils;
import org.crossref.accessindicators.Program;
import org.crossref.schema._5_3.DoiBatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class DummyController {

    @GetMapping("/version")
    public String getVersion() {
        return "0.1";
    }

    @GetMapping("/crossref")
    public void getXmlTest(final HttpServletRequest request,
                             final HttpServletResponse response) throws Exception {

        DoiBatch batch = new DoiBatch();

        batch.setVersion("5.3.0");

        DoiBatch.Head head = new DoiBatch.Head();
        head.setDoiBatchId(new BigInteger("202309011352"));
        head.setTimestamp(new BigInteger("202309011352"));
        head.setRegistrant("Natural Resources Canada");

        DoiBatch.Head.Depositor depositor = new DoiBatch.Head.Depositor();
        depositor.setDepositorName("GEOSCAN Database Team");
        depositor.setEmailAddress("geoscan@nrcan-rncan.gc.ca");
        head.setDepositor(depositor);

        batch.setHead(head);

        DoiBatch.Body body = new DoiBatch.Body();

        List<DoiBatch.Body.ReportPaper> reportPapers = body.getReportPaper();

        DoiBatch.Body.ReportPaper reportPaper = new DoiBatch.Body.ReportPaper();

        DoiBatch.Body.ReportPaper.ReportPaperMetadata metadata =
                new DoiBatch.Body.ReportPaper.ReportPaperMetadata();

        metadata.setLanguage("en");
        metadata.setEditionNumber("prelim., surfic");

        // Need to handle Orgs and Persons here
        DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors contributors = new DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors();
        List<DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName> authors =
                contributors.getPersonName();

        DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName author =
                new DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName();
        author.setSequence("first");
        author.setContributorRole("author");
        author.setGivenName("S K");
        author.setSurname("Frey");
        authors.add(author);

        author = new DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName();
        author.setSequence("additional");
        author.setContributorRole("author");
        author.setGivenName("H A J");
        author.setSurname("Russell");

        DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName.Affiliations affiliations =
                new DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName.Affiliations();

        DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName.Affiliations.Institution institution =
                new DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName.Affiliations.Institution();

        institution.setInstitutionName("Natural Resources Canada");

        DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName.Affiliations.Institution.InstitutionId institutionId =
                new DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName.Affiliations.Institution.InstitutionId();

        institutionId.setType("ror");
        institutionId.setValue("https://ror.org/05hepy730");

        institution.setInstitutionId(institutionId);

        affiliations.setInstitution(institution);
        author.setAffiliations(affiliations);

        DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName.ORCID orcid =
                new DoiBatch.Body.ReportPaper.ReportPaperMetadata.Contributors.PersonName.ORCID();

        orcid.setValue("https://orcid.org/0000-0002-7688-2260");

        author.setORCID(orcid);

        authors.add(author);

        metadata.setContributors(contributors);

        DoiBatch.Body.ReportPaper.ReportPaperMetadata.Titles titles = new DoiBatch.Body.ReportPaper.ReportPaperMetadata.Titles();

        titles.setTitle("Canada1Water spring 2023 progress meeting summary report");

        metadata.setTitles(titles);

        DoiBatch.Body.ReportPaper.ReportPaperMetadata.PublicationDate publicationDate =
                new DoiBatch.Body.ReportPaper.ReportPaperMetadata.PublicationDate();

        publicationDate.setYear(2023);

        metadata.setPublicationDate(publicationDate);

        DoiBatch.Body.ReportPaper.ReportPaperMetadata.PublisherItem publisherItem =
                new DoiBatch.Body.ReportPaper.ReportPaperMetadata.PublisherItem();

        DoiBatch.Body.ReportPaper.ReportPaperMetadata.PublisherItem.ItemNumber itemNumber =
                new DoiBatch.Body.ReportPaper.ReportPaperMetadata.PublisherItem.ItemNumber();

        itemNumber.setItemNumberType("Geological Survey of Canada, Open File");
        itemNumber.setValue(8991);

        publisherItem.setItemNumber(itemNumber);
        metadata.setPublisherItem(publisherItem);

        Program program = new Program();
        program.setName("AccessIndicators");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

        Program.FreeToRead freeToRead = new Program.FreeToRead();
        LocalDate.parse("2023-08-30", formatter);
        freeToRead.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                LocalDate.parse("2023-08-30", formatter).toString()));
        program.setFreeToRead(freeToRead);

        Program.LicenseRef licenseRef = new Program.LicenseRef();
        licenseRef.setAppliesTo("vor");
        licenseRef.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                LocalDate.parse("2023-08-30", formatter).toString()));
        licenseRef.setValue("https://open.canada.ca/en/open-government-licence-canada");
        program.setLicenseRef(licenseRef);

        metadata.setProgram(program);

        DoiBatch.Body.ReportPaper.ReportPaperMetadata.DoiData doiData =
                new DoiBatch.Body.ReportPaper.ReportPaperMetadata.DoiData();

        doiData.setDoi("10.4095/306288");
        doiData.setResource("https://geoscan.nrcan.gc.ca/starweb/geoscan/servlet.starweb?path=geoscan/fulle.web&amp;search1=R=306288");

        metadata.setDoiData(doiData);

        Abstract abstractEn = new Abstract();
        Abstract.P abstractText = new Abstract.P();
        abstractText.setLang("en");
        abstractText.setValue("This is my abstract");
        abstractEn.setP(abstractText);

        List<Abstract> abstracts = metadata.getAbstract();
        abstracts.add(abstractEn);

        Abstract abstractFr = new Abstract();
        abstractText = new Abstract.P();
        abstractText.setLang("fr");
        abstractText.setValue("Ce nouveau produit cartographique de la g&#233;ologie des formations");
        abstractEn.setP(abstractText);

        abstracts.add(abstractFr);

        reportPaper.setReportPaperMetadata(metadata);
        reportPapers.add(reportPaper);
        batch.setBody(body);

        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "attachment; filename=" + "temp" + ".xml");
        final JAXBContext jaxbContext = JAXBContext.newInstance(DoiBatch.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.crossref.org/schema/5.3.0 http://data.crossref.org/schemas/crossref5.3.0.xsd");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(batch, response.getOutputStream());
    }
}
