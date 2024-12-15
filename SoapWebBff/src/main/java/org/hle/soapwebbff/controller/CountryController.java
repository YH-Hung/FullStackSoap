package org.hle.soapwebbff.controller;

import org.hle.soapwebbff.CountryClient;
import org.hle.soapwebbff.wsdl.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    CountryClient countryClient;

    @GetMapping
    public Country getCountry() {
        var country = countryClient.getCountry("Poland").getCountry();
        return country;
    }
}
