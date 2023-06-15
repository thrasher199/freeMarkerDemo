package com.nasrol.freeMarkerDemo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "template_configuration")
public class TemplateConfiguration {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "template_name")
    private String templateName;

    @Lob
    @Column(name = "template_content")
    private String templateContent;

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}