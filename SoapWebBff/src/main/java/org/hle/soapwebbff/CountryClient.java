package org.hle.soapwebbff;

import org.hle.soapwebbff.wsdl.GetCountryRequest;
import org.hle.soapwebbff.wsdl.GetCountryResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountryClient extends WebServiceGatewaySupport {

    public GetCountryResponse getCountry(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }
}
