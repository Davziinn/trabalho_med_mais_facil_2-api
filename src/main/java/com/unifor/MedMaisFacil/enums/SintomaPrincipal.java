package com.unifor.MedMaisFacil.enums;

public enum SintomaPrincipal {
    DOR_TORACICA("Dor no peito"),
    DISPNEIA("Falta de ar"),
    FEBRE("Febre"),
    DOR_ABDOMINAL("Dor de barriga"),
    CEFALEIA("Dor de cabeça");

    private final String label;

    SintomaPrincipal(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
