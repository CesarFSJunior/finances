package com.finances.finance.domain.entities.user;

import java.util.Date;
import java.util.UUID;

public record UserDto(UUID id, String name, String email, Date birthday) {
}
