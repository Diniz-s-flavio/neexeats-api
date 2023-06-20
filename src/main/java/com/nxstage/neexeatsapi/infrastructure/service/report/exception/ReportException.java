package com.nxstage.neexeatsapi.infrastructure.service.report.exception;

public class ReportException extends RuntimeException{
    private static  final  long serialVersionUID = 1L;

    public ReportException(String message) {
        super(message);
    }

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
