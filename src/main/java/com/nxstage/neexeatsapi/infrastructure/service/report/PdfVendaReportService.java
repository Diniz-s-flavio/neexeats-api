package com.nxstage.neexeatsapi.infrastructure.service.report;

import com.nxstage.neexeatsapi.domain.filter.VendaDiariaFilter;
import com.nxstage.neexeatsapi.domain.repository.VendaQueryService;
import com.nxstage.neexeatsapi.domain.service.VendaReportService;
import com.nxstage.neexeatsapi.infrastructure.service.report.exception.ReportException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class PdfVendaReportService implements VendaReportService {

    @Autowired
    private VendaQueryService vendaQueryService;
    @Override
    public byte[] issueDailySales(VendaDiariaFilter filter, String timeOffset) {
        try {
            var inputStream =  this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

            var parameters = new HashMap<String, Object>();
            parameters.put("REPORT_LOCALE",new Locale("pt", "BR"));

            var vendasDiarias = vendaQueryService.getDailySales(filter, timeOffset);
            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

            var jasperPrint = JasperFillManager.fillReport(inputStream,parameters,dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir relatório de vendas diárias",e);
        }
    }
}
