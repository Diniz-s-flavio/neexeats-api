package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.domain.filter.VendaDiariaFilter;
import com.nxstage.neexeatsapi.domain.model.dto.VendaDiaria;
import com.nxstage.neexeatsapi.domain.repository.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> getDailySales(VendaDiariaFilter vendasDiariasFilter,
                                           @RequestParam(required = false,defaultValue = "+00:00") String timeOffset){
        return  vendaQueryService.getDailySales(vendasDiariasFilter, timeOffset);
    }
}
