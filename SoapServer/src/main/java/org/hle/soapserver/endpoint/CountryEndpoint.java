package org.hle.soapserver.endpoint;

import org.hle.soapserver.gen.Country;
import org.hle.soapserver.gen.Currency;
import org.hle.soapserver.gen.GetCountryRequest;
import org.hle.soapserver.gen.GetCountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    @PayloadRoot(namespace = "http://hle.org/soapserver/gen", localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        Country country = new Country();
        country.setCapital("Never Winter");
        country.setName("Azurlas");
        country.setPopulation(50000);
        country.setCurrency(Currency.EUR);

        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(country);

        return response;
    }
}
