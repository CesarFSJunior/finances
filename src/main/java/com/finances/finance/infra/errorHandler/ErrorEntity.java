package com.finances.finance.infra.errorHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorEntity {

    private String message;
    private String error;
    private int Status;
    private Date timestamp;

}
