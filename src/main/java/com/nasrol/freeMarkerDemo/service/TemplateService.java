package com.nasrol.freeMarkerDemo.service;

import com.nasrol.freeMarkerDemo.domain.TemplateConfiguration;
import com.nasrol.freeMarkerDemo.repository.TemplateConfigurationRepository;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    private final TemplateConfigurationRepository templateConfigurationRepository;

    public TemplateService(TemplateConfigurationRepository templateConfigurationRepository) {
        this.templateConfigurationRepository = templateConfigurationRepository;
    }

    public TemplateConfiguration getTemplateString(String templateName){
        return templateConfigurationRepository.findByTemplateName(templateName);
    }
}
