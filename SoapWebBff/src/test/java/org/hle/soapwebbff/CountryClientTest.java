package org.hle.soapwebbff;

import jakarta.jws.WebService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.webservices.client.AutoConfigureWebServiceClient;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

@SpringBootTest
class CountryClientTest {

    MockWebServiceServer server;

    @Autowired
    CountryClient countryClient;

    @BeforeEach
    void beforeEach() {
        server = MockWebServiceServer.createServer(countryClient);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.hle.soapwebbff.wsdl");
        countryClient.setMarshaller(marshaller);
        countryClient.setUnmarshaller(marshaller);
    }

    @Test
    void mockServerCall() {
        server.expect(payload(new StringSource(rqstPayload("USA"))))
                .andRespond(withPayload(new StringSource(respPayload())));

        assertThat(countryClient.getCountry("USA").getCountry().getCapital()).isEqualTo("Never Winter");

        server.verify();
    }

    private String rqstPayload(String name) {
        String template = """
                 <getCountryRequest xmlns="http://hle.org/soapserver/gen">
                    <name>%s</name>
                 </getCountryRequest>
                """;

        return String.format(template, name);
    }

    private String respPayload() {
        String template = """
                <getCountryResponse xmlns="http://hle.org/soapserver/gen">
                 <country>
                    <name>Azurlas</name>
                    <population>50000</population>
                    <capital>Never Winter</capital>
                    <currency>EUR</currency>
                 </country>
                </getCountryResponse>
                """;

        return template;
    }
}