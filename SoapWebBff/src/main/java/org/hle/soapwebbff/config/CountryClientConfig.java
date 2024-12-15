package org.hle.soapwebbff.config;

import org.hle.soapwebbff.CountryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CountryClientConfig {

    @Value("${country.ws.url}")
    private String baseUrl;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.hle.soapwebbff.wsdl");
        return marshaller;
    }

    @Bean
    public CountryClient countryClient(Jaxb2Marshaller marshaller) {
        CountryClient countryClient = new CountryClient();
        countryClient.setDefaultUri(baseUrl);
        countryClient.setMarshaller(marshaller);
        countryClient.setUnmarshaller(marshaller);
        return countryClient;
    }
}
