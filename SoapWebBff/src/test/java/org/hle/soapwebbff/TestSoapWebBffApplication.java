package org.hle.soapwebbff;

import org.springframework.boot.SpringApplication;

public class TestSoapWebBffApplication {

    public static void main(String[] args) {
        SpringApplication.from(SoapWebBffApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
