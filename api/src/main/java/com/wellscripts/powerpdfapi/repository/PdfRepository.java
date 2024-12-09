package com.wellscripts.powerpdfapi.repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wellscripts.powerpdfapi.model.PdfFile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PdfRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File jsonFile = new File("database.json"); // Caminho do arquivo JSON

    public PdfRepository() {
        // Registra suporte ao JavaTimeModule para LocalDate
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    // Carregar dados do JSON
    private List<PdfFile> loadData() throws IOException {
        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
            return new ArrayList<>();
        }
        return objectMapper.readValue(jsonFile, new TypeReference<List<PdfFile>>() {});
    }

    // Salvar dados no JSON
    private void saveData(List<PdfFile> pdfFiles) throws IOException {
        objectMapper.writeValue(jsonFile, pdfFiles);
    }

    // Salvar um novo PDF
    public void save(PdfFile pdfFile) throws IOException {
        List<PdfFile> pdfFiles = loadData();
        pdfFiles.add(pdfFile);
        saveData(pdfFiles);
    }

    // Listar todos os PDFs
    public List<PdfFile> findAll() throws IOException {
        return loadData();
    }

    // Buscar PDFs por data de cadastro
    public List<PdfFile> findByDate(String data) throws IOException {
        return loadData().stream()
                .filter(pdf -> pdf.getDataCadastroPdf().toString().equals(data))
                .collect(Collectors.toList());
    }
}
