package com.nxstage.neexeatsapi.infrastructure.service.report;

import com.nxstage.neexeatsapi.domain.filter.VendaDiariaFilter;
import com.nxstage.neexeatsapi.domain.service.VendaReportService;
import org.springframework.stereotype.Service;

@Service
public class PdfVendaReportService implements VendaReportService {
    @Override
    public byte[] issueDailySales(VendaDiariaFilter filter, String timeOffset) {
        return new byte[0];
    }
}
