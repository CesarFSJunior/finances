package com.finances.finance.domain.entities.user;

import java.util.Date;

public record UserRegisterDto(String name, String email, String password, Date birthday) {
}
