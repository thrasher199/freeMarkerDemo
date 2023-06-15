package com.nasrol.freeMarkerDemo.service;

import com.nasrol.freeMarkerDemo.domain.LoanApplication;
import com.nasrol.freeMarkerDemo.domain.TemplateConfiguration;
import com.nasrol.freeMarkerDemo.repository.TemplateConfigurationRepository;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class FreeMarkerServiceTest {

    @Autowired
    private FreeMarkerService service;

    @Autowired
    private TemplateConfigurationRepository repository;

    protected void initPlainStringTemplate(){
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateConfiguration.setId(1L);
        templateConfiguration.setTemplateName("SMS_TEMPLATE");
        templateConfiguration.setTemplateContent("Loan Application for ${applicantName} and ${(applicantAddress)!}");
        repository.save(templateConfiguration);
    }

    protected void initHtmlTemplate(){
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateConfiguration.setId(2L);
        templateConfiguration.setTemplateName("EMAIL_TEMPLATE");
        templateConfiguration.setTemplateContent("<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <p>\n" +
                "            Salam Sejahtera <b>${applicantName}</b>\n" +
                "        </p>\n" +
                "        <p>\n" +
                "\t\t\tAlamat : ${applicantAddress}\n" +
                "        </p>\n" +
                "    </body>\n" +
                "</html>");
        repository.save(templateConfiguration);
    }



    @Test
    public void testSMSContentTemplate() throws TemplateException, IOException {
        initPlainStringTemplate();
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setId(1L);
        loanApplication.setApplicantName("Applicant 1");
        loanApplication.setApplicantAddress("Address 1");

        var content = service.generateContentFromTemplate("SMS_TEMPLATE", loanApplication);
        Assertions.assertEquals("Loan Application for Applicant 1 and Address 1", content);
    }

    @Test
    public void testSMSContentTemplateAddressIsNull() throws TemplateException, IOException {
        initPlainStringTemplate();
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setId(1L);
        loanApplication.setApplicantName("Applicant 1");

        var content = service.generateContentFromTemplate("SMS_TEMPLATE", loanApplication);
        Assertions.assertEquals("Loan Application for Applicant 1 and", content);
    }

    @Test
    public void testEmailTemplate() throws TemplateException, IOException {
        initHtmlTemplate();
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setId(1L);
        loanApplication.setApplicantName("Applicant 1");
        loanApplication.setApplicantAddress("Address 1");

        var content = service.generateContentFromTemplate("EMAIL_TEMPLATE", loanApplication);
        Assertions.assertEquals("<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <p>\n" +
                "            Salam Sejahtera <b>Applicant 1</b>\n" +
                "        </p>\n" +
                "        <p>\n" +
                "\t\t\tAlamat : Address 1\n" +
                "        </p>\n" +
                "    </body>\n" +
                "</html>", content);

    }


}