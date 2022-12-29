package com.wliafe.common.config.my;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class UimConfig {
    @Value("${uim.title}")
    private String title;
    @Value("${uim.description}")
    private String description;
    @Value("${uim.author}")
    private String author;
    @Value("${uim.version}")
    private String version;
}
