package com.wellscripts.powerpdfapi.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellscripts.powerpdfapi.model.PdfFile;
import com.wellscripts.powerpdfapi.repository.PdfRepository;

@Service
public class PdfService {
    @Autowired
    private PdfRepository pdfRepository;

    // Diretório onde os PDFs serão armazenados
    private static final String PDF_DIRECTORY = "uploads/pdfs/";

    // Cadastrar novo PDF
    public void cadastrarPdf(String nomePdf, byte[] arquivoPdf) throws IOException {
        // Garantir que o nome do arquivo tenha a extensão .pdf
        if (!nomePdf.toLowerCase().endsWith(".pdf")) {
            nomePdf = nomePdf + ".pdf";  // Adiciona a extensão .pdf
        }

        // Cria diretório se não existir
        File dir = new File(PDF_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Definir o caminho do arquivo PDF
        String filePath = PDF_DIRECTORY + nomePdf;

        // Salvar o arquivo PDF no diretório especificado
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(arquivoPdf);
        }

        // Criar objeto PdfFile com o caminho do arquivo e a data de cadastro
        PdfFile pdfFile = new PdfFile();
        pdfFile.setNomePdf(nomePdf);
        pdfFile.setCaminhoPdf(filePath);  // Salva apenas o caminho do arquivo
        pdfFile.setDataCadastroPdf(LocalDate.now());

        // Salvar no repositório
        pdfRepository.save(pdfFile);
    }

    // Listar todos os PDFs
    public List<PdfFile> listarTodos() throws IOException {
        return pdfRepository.findAll();
    }

    // Listar PDFs por data de cadastro
    public List<PdfFile> listarPorData(String data) throws IOException {
        return pdfRepository.findByDate(data);
    }
}
