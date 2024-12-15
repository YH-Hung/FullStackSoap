package org.hle.soapwebbff.dto;

import java.util.List;

public record ProductDto(String id, List<ProductData> data) {
}
