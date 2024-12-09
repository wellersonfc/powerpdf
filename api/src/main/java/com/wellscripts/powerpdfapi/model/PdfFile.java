package com.wellscripts.powerpdfapi.model;

import java.time.LocalDate;

public class PdfFile {
    private String nomePdf;
    private String caminhoPdf;  // Armazenará o caminho do arquivo
    private LocalDate dataCadastroPdf;

    // Getters e Setters
    public String getNomePdf() {
        return nomePdf;
    }

    public void setNomePdf(String nomePdf) {
        this.nomePdf = nomePdf;
    }

    public String getCaminhoPdf() {
        return caminhoPdf;
    }

    public void setCaminhoPdf(String caminhoPdf) {
        this.caminhoPdf = caminhoPdf;
    }

    public LocalDate getDataCadastroPdf() {
        return dataCadastroPdf;
    }

    public void setDataCadastroPdf(LocalDate dataCadastroPdf) {
        this.dataCadastroPdf = dataCadastroPdf;
    }
}
