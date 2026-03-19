package com.example.pdfdemo.controller;

import com.example.pdfdemo.service.PdfService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@RestController
public class PdfController {

    private final PdfService pdfService;

    // Cache the templates (optional but recommended)
    private final String indexHtml;
    private final String resultHtml;

    public PdfController(PdfService pdfService) throws IOException {
        this.pdfService = pdfService;
        this.indexHtml  = readResource("/pages/index.html");
        this.resultHtml = readResource("/pages/result.html");
    }

    private static String readResource(String path) throws IOException {
        var res = new ClassPathResource(path);
        return Files.readString(res.getFile().toPath(), StandardCharsets.UTF_8);
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        // Return the page as-is (your index.html contains the form)
        return indexHtml;
    }

    @GetMapping(value = "/result", produces = MediaType.TEXT_HTML_VALUE)
    public String result(@RequestParam(name = "text", required = false) String text) {
        String safeText = StringUtils.hasText(text) ? text : "No content provided.";
        String encoded = URLEncoder.encode(safeText, StandardCharsets.UTF_8);

        String pdfUrlInline   = "/pdf?text=" + encoded;
        String pdfUrlDownload = "/pdf?download=true&text=" + encoded;

        // Replace simple placeholders in result.html like {{INLINE_URL}} and {{DOWNLOAD_URL}}
        return resultHtml
                .replace("{{INLINE_URL}}", pdfUrlInline)
                .replace("{{DOWNLOAD_URL}}", pdfUrlDownload);
    }

    @GetMapping(value = "/pdf", produces = "application/pdf")
    public ResponseEntity<byte[]> pdf(
            @RequestParam(name = "text", required = false) String text,
            @RequestParam(name = "download", defaultValue = "false") boolean download) throws IOException {

        byte[] data = pdfService.generatePdf(text);
        String filename = "generated.pdf";

        ContentDisposition cd = ContentDisposition
                .builder(download ? "attachment" : "inline")
                .filename(filename, StandardCharsets.UTF_8)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, cd.toString())
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }
}