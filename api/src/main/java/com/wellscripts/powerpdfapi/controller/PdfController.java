package com.wellscripts.powerpdfapi.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wellscripts.powerpdfapi.model.PdfFile;
import com.wellscripts.powerpdfapi.service.PdfService;

@RestController
@RequestMapping("/api/pdfs")
@CrossOrigin(origins = "http://localhost:3000") 
public class PdfController {

    @Autowired
    private PdfService pdfService;
    private static final String PDF_DIRECTORY = "uploads/pdfs/";

    // Endpoint para cadastrar novo PDF
@PostMapping("/cadastrar")
public ResponseEntity<String> cadastrarPdf(@RequestParam("nomePdf") String nomePdf,
                                           @RequestParam("arquivoPdf") MultipartFile arquivoPdf) {
    try {
        System.out.println("Recebendo dados: nomePdf=" + nomePdf + ", tamanho do arquivo=" + arquivoPdf.getSize());
        pdfService.cadastrarPdf(nomePdf, arquivoPdf.getBytes());
        System.out.println("Cadastro realizado com sucesso.");
        // Retorno de sucesso com status 200
        return ResponseEntity.status(HttpStatus.OK).body("PDF cadastrado com sucesso!");
    } catch (IOException e) {
        System.err.println("Erro ao cadastrar PDF: " + e.getMessage());
        
        // Retorno de erro com status 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Erro ao cadastrar o PDF: " + e.getMessage());
    }
}


    // Endpoint para listar todos os PDFs
    @GetMapping("/listar")
    public ResponseEntity<List<PdfFile>> listarTodos() {
        try {
            return ResponseEntity.ok(pdfService.listarTodos());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // Endpoint para listar PDFs por data
    @GetMapping("/listarPorData")
    public ResponseEntity<List<PdfFile>> listarPorData(@RequestParam("data") String data) {
        try {
            return ResponseEntity.ok(pdfService.listarPorData(data));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
    
    // Endpoint para obter PDF
    @GetMapping("/{nomePdf}")
    public ResponseEntity<FileSystemResource> getPdf(@PathVariable String nomePdf) {
        File file = new File(PDF_DIRECTORY + nomePdf);
    
        if (!file.exists() || !file.isFile()) {
            return ResponseEntity.notFound().build();
        }
    
        FileSystemResource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "inline; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
    
}
