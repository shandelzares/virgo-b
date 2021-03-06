package com.virgo.bootstrap;

import com.virgo.common.JsonUtils;
import com.virgo.common.swagger.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.virgo")
@EnableJpaRepositories(basePackages = "com.virgo.*.repository")
@EnableMongoRepositories(basePackages = "com.virgo.exam.repository")
@EntityScan(basePackages = "com.virgo.*.model")
@Import({SwaggerConfig.class, JsonUtils.class})
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
