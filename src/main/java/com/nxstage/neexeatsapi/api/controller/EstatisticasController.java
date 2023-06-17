package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.domain.filter.VendaDiariaFilter;
import com.nxstage.neexeatsapi.domain.model.dto.VendaDiaria;
import com.nxstage.neexeatsapi.domain.repository.VendaQueryService;
import com.nxstage.neexeatsapi.domain.service.VendaReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;
    @Autowired
    private VendaReportService vendaReportService;

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> getDailySales(VendaDiariaFilter vendasDiariasFilter,
                                           @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
        return  vendaQueryService.getDailySales(vendasDiariasFilter, timeOffset);
    }
    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getDailySalesPDF(VendaDiariaFilter filtro, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
        byte[] bytesPdf = vendaReportService.issueDailySales(filtro, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }
}
