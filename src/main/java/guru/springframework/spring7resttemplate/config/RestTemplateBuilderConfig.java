package guru.springframework.spring7resttemplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.boot.restclient.autoconfigure.RestTemplateBuilderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateBuilderConfig {


    @Value("${rest.template.rootUrl}")
    String rootUrl;

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public RestTemplateBuilderConfig(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @Bean
    OAuth2AuthorizedClientManager auth2AuthorizedClientManager() {


        var authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build();

        var authorizedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager
                (clientRegistrationRepository, oAuth2AuthorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        return authorizedClientManager;
    }

    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer) {

        assert rootUrl != null;

        return configurer.configure(new RestTemplateBuilder())
                .uriTemplateHandler(new DefaultUriBuilderFactory(rootUrl));
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
