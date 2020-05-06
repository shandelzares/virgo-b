package com.virgo.member;

import com.virgo.common.AppConfig;
import com.virgo.member.service.AliSmsClient;
import com.virgo.member.service.SmsClient;
import com.virgo.member.service.TencentSmsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class MemberConfig {

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("会员")
                .apiInfo(new ApiInfoBuilder()
                        .title("会员")
                        .version("1.0")
                        .build())
                .useDefaultResponseMessages(true)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(MemberConfig.class.getPackageName()))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public SmsClient smsClient(AppConfig appConfig) throws Exception {
        switch (appConfig.getSms().getType()) {
            case ALIYUN:
                return new AliSmsClient();
            case TENCENT:
                return new TencentSmsClient();
            default:
                throw new Exception("短信发送client未找到");
        }
    }
}
