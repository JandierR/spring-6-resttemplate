package guru.springframework.spring7resttemplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.boot.restclient.autoconfigure.RestTemplateBuilderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateBuilderConfig {


    @Value("${rest.template.rootUrl}")
    String rootUrl;

    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer) {

        assert rootUrl != null;

        RestTemplateBuilder builder = configurer.configure(new RestTemplateBuilder());
        DefaultUriBuilderFactory uriBuilderFactory =
                new DefaultUriBuilderFactory(rootUrl);

        return builder.uriTemplateHandler(uriBuilderFactory);
    }

//    @Bean
//    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer) {
//
//        return configurer.configure(new RestTemplateBuilder()
//                        .uriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080/")))
//                .connectTimeout(Duration.ofSeconds(5))
//                .readTimeout(Duration.ofSeconds(2));
//    }

//    @Bean
//    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer) {
//        return configurer.configure(new RestTemplateBuilder())
//                .connectTimeout(Duration.ofSeconds(5))
//                .readTimeout(Duration.ofSeconds(2));
//    }
}
