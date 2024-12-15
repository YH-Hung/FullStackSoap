package org.hle.soapwebbff.controller;

import lombok.extern.slf4j.Slf4j;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @PostMapping
    public String uploadExcel(@RequestParam("file") MultipartFile file) {

        try (InputStream is = file.getInputStream(); ReadableWorkbook wb = new ReadableWorkbook(is)) {
            return wb.getSheets().map(s -> {
                try (Stream<Row> rows = s.openStream()) {
                    return rows.map(getRowAction(s.getName())).collect(Collectors.joining("\n"));
                } catch (IOException e) {
                    return "Error when openStream for %s".formatted(s.getName());
                }
            }).collect(Collectors.joining("\n"));

        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @GetMapping
    public String check() {
        return "Route existed!!!";
    }

    private static Function<Row, String> getRowAction(String sheetName) {
        if (sheetName.contains("product")) {
            return r -> r.getCellAsString(0).orElse("EMPTY!!!");
        } else if (sheetName.contains("data")) {
            return r -> {
                String name = r.getCellAsString(1).orElse("EMPTY Name!!!");
                BigDecimal value = r.getCellAsNumber(2).orElse(null);
                return "name: %s value: %f".formatted(name, value);
            };
        } else {
            return r -> "Unknown sheet name!!!";
        }
    }
}
