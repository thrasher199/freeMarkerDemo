package com.nasrol.freeMarkerDemo.repository;

import com.nasrol.freeMarkerDemo.domain.TemplateConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateConfigurationRepository extends JpaRepository<TemplateConfiguration, Long> {
    TemplateConfiguration findByTemplateName(String templateName);
}