package com.example.ProjetoAula05.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AtividadeDTO {

    private Long id;
    @Size(max = 80, message = "tamanho do 'titulo' acima do permitido")
    @NotBlank(message = "'titulo' deve conter algum valor")
    private String titulo;
    private String descricao;

    private Boolean finalizado;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

}
