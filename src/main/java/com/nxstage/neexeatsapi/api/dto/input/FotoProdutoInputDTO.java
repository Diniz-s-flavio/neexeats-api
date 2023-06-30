package com.nxstage.neexeatsapi.api.dto.input;

import com.nxstage.neexeatsapi.core.validation.FileSize;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FotoProdutoInputDTO {
    @NotNull
    @FileSize(max = "500KB")
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;
}
