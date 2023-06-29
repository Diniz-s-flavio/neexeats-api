package com.nxstage.neexeatsapi.api.dto.input;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FotoProdutoInputDTO {
    private String descricao;
    private MultipartFile arquivo;
}
