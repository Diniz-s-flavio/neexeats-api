package com.nxstage.neexeatsapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;

    private String userMessage;
    private LocalDateTime timestamp;
    private List<ObjectProblem> objects;

    @Getter
    @Builder
    public static class ObjectProblem {
        private String name;
        private String userMessage;
    }
}
