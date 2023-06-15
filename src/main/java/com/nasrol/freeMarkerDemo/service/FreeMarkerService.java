package com.nasrol.freeMarkerDemo.service;

import com.nasrol.freeMarkerDemo.domain.LoanApplication;
import com.nasrol.freeMarkerDemo.domain.TemplateConfiguration;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class FreeMarkerService {

    private final TemplateService templateService;

    private final Configuration freeMarkerConfiguration;

    public FreeMarkerService(TemplateService templateService, Configuration freeMarkerConfiguration) {
        this.templateService = templateService;
        this.freeMarkerConfiguration = freeMarkerConfiguration;
    }

    public String generateContentFromTemplate(String templateName, LoanApplication loanApplication) throws IOException, TemplateException {
        TemplateConfiguration templateConfiguration = templateService.getTemplateString(templateName);
        Template template = new Template(templateConfiguration.getTemplateName(),new StringReader(templateConfiguration.getTemplateContent()), freeMarkerConfiguration);
        Map<String, String> dataMap = new HashMap<>();
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, loanApplication);
    }
}
