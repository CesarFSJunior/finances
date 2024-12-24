package com.finances.finance.domain.entities.modalidade;

public enum TipoModalidade {

    DEBITO("debito"),
    CREDITO("credito"),
    DINHEIRO("dinheiro");

    private String role;


    TipoModalidade(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
