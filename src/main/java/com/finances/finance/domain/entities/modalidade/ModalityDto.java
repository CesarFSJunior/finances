package com.finances.finance.domain.entities.modalidade;

import com.finances.finance.domain.entities.user.UserDto;

import java.util.UUID;

public record ModalityDto(UUID idCartao, String nomeCartao, ModalityType modalityType, UserDto owner) {
}
