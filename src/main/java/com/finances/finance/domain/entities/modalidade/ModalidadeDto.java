package com.finances.finance.domain.entities.modalidade;

import com.finances.finance.domain.entities.user.UserDto;

import java.util.UUID;

public record ModalidadeDto(UUID idCartao, String nomeCartao, TipoModalidade tipoModalidade, UserDto owner) {
}
