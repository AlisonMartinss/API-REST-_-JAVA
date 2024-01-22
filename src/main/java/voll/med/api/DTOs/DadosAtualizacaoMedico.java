package voll.med.api.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String email,
        DadosEndereco endereco) {
}
