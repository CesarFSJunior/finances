package com.finances.finance.domain.entities.modalidade;

public enum ModalityType {

    DEBITO("debito"),
    CREDITO("credito"),
    DINHEIRO("dinheiro");

    private String role;


    ModalityType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
