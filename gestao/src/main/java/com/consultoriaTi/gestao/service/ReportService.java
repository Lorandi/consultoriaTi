package com.consultoriaTi.gestao.service;


import com.consultoriaTi.gestao.dto.ReportDTO;
import com.consultoriaTi.gestao.enums.AllocationStatusEnum;
import com.consultoriaTi.gestao.enums.ProfessionalStatusEnum;
import com.consultoriaTi.gestao.helper.MessageHelper;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.mail.util.ByteArrayDataSource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import io.vavr.control.Try;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.consultoriaTi.gestao.exception.ErrorCodeEnum.ERROR_GENERATING_REPORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final MessageHelper messageHelper;
    private final ProfessionalService professionalService;
    private final AllocationService allocationService;

    public ReportDTO create() {
        log.info("Creating report");
        BigDecimal revenue = allocationService.calculateRevenue();
        BigDecimal expenses = professionalService.calculateExpenses();

        return ReportDTO.builder()
                .totalProfessionals(professionalService.count())
                .professionalsAllocated(professionalService.countByProfessionalStatus(ProfessionalStatusEnum.ALLOCATED))
                .professionalsNotAllocated(professionalService.countByProfessionalStatus(ProfessionalStatusEnum.NOT_ALLOCATED))
                .totalActiveAllocations(allocationService.countByAllocationStatus(AllocationStatusEnum.ACTIVE))
                .totalScheduledAllocations(allocationService.countByAllocationStatus(AllocationStatusEnum.SCHEDULED))
                .revenue(revenue)
                .expenses(expenses)
                .profit(revenue.subtract(expenses))
                .build();
    }

    public ByteArrayDataSource createPdf(HttpServletResponse response) {
        ReportDTO reportRequest = create();
        final var returnValue = new AtomicReference<ByteArrayDataSource>();
        Try.run(() -> {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("totalProfessionals", reportRequest.getTotalProfessionals().toString());
            parameters.put("professionalsAllocated", reportRequest.getProfessionalsAllocated().toString());
            parameters.put("professionalsNotAllocated", reportRequest.getProfessionalsNotAllocated().toString());
            parameters.put("totalActiveAllocations", reportRequest.getTotalActiveAllocations().toString());
            parameters.put("totalScheduledAllocations", reportRequest.getTotalScheduledAllocations().toString());
            parameters.put("revenue", reportRequest.getRevenue().toString());
            parameters.put("expenses", reportRequest.getExpenses().toString());
            parameters.put("profit", reportRequest.getProfit().toString());

            InputStream is = this.getClass().getResourceAsStream("/reports/Relatorio.html");
            String template = buildTemplate(parameters, is);
            Document document = Jsoup.parse(template, "UTF-8");
            document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.toStream(out);
            builder.withW3cDocument(new W3CDom().fromJsoup(document), "/");
            builder.run();
            response.setContentType("application/x-download");
            response.addHeader("Content-disposition", "attachment; filename=Relatorio_PDF.pdf");
            OutputStream responseOut = response.getOutputStream();
            responseOut.write(out.toByteArray());
            returnValue.set(new ByteArrayDataSource(out.toByteArray(), "application/pdf; method=REQUEST; name=\"Relatorio_PDF.pdf\""));
            out.close();
        }).onFailure(throwable -> {
            log.error(throwable.getMessage());
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_GENERATING_REPORT));
        });
        return returnValue.get();
    }

    private String buildTemplate(Map<String, Object> props, InputStream templateFile) throws IOException {
        var template = new String(templateFile.readAllBytes());
        for (Map.Entry<String, Object> prop : props.entrySet()) {
            template = template.replace("${" + prop.getKey() + "}", (String) prop.getValue());
        }
        return template;
    }



}
