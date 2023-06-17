package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] issueDailySales(VendaDiariaFilter filter, String timeOffset);
}
