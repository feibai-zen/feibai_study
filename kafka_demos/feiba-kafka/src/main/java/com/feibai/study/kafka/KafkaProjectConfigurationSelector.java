package com.feibai.study.kafka;

import com.feibai.study.kafka.config.ProjectAConsumerConfiguration;
import com.feibai.study.kafka.config.ProjectAPublisherConfiguraton;
import com.feibai.study.kafka.config.ProjectBConsumerConfiguration;
import com.feibai.study.kafka.config.ProjectBPublisherConfiguraton;
import com.feibai.study.kafka.controller.KafkaEndPointConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KafkaProjectConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String projectName = System.getProperty("feibai.project");
        List<String> imports = new ArrayList<>(Arrays.asList(KafkaEndPointConfiguration.class.getName()));

        switch (projectName == null ? "" : projectName) {
            case "projectA":
                imports.addAll(Arrays.asList(ProjectAPublisherConfiguraton.class.getName(),
                        ProjectAConsumerConfiguration.class.getName()));
                break;
            case "projectB":
            default:
                imports.addAll(Arrays.asList(ProjectBPublisherConfiguraton.class.getName(),
                        ProjectBConsumerConfiguration.class.getName()));
                break;
        }

        return new String[0];
    }
}