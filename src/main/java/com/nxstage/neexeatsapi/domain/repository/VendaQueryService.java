package com.nxstage.neexeatsapi.domain.repository;

import com.nxstage.neexeatsapi.domain.filter.VendaDiariaFilter;
import com.nxstage.neexeatsapi.domain.model.dto.VendaDiaria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VendaQueryService {
    List<VendaDiaria> getDailySales(VendaDiariaFilter filter, String timeOffset);
}
