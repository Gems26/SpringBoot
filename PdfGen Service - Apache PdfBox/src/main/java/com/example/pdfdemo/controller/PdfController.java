package com.example.pdfdemo.controller;

import com.example.pdfdemo.service.PdfService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class PdfController {

    private final PdfService pdfService;

    // Cache the templates after reading via classpath streams
    private final String indexHtml;
    private final String resultHtml;

    public PdfController(PdfService pdfService) throws IOException {
        this.pdfService = pdfService;
        this.indexHtml  = readResource("pages/index.html");
        this.resultHtml = readResource("pages/result.html");
    }

    /**
     * Read a classpath resource as String using InputStream (works in fat JARs).
     * DO NOT use resource.getFile() — it fails inside an executable JAR.
     */
    private static String readResource(String classpathRelativePath) throws IOException {
        ClassPathResource res = new ClassPathResource(classpathRelativePath);
        try (var in = res.getInputStream()) {
            byte[] bytes = StreamUtils.copyToByteArray(in);
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        return indexHtml;
    }

    @GetMapping(value = "/result", produces = MediaType.TEXT_HTML_VALUE)
    public String result(@RequestParam(name = "text", required = false) String text) {
        String safeText = StringUtils.hasText(text) ? text : "No content provided.";
        String encoded = URLEncoder.encode(safeText, StandardCharsets.UTF_8);

        String pdfUrlInline   = "/pdf?text=" + encoded;
        String pdfUrlDownload = "/pdf?download=true&text=" + encoded; // use & here (template is HTML)

        // Replace placeholders in result.html like {{INLINE_URL}} and {{DOWNLOAD_URL}}
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
                .filename(filename, StandardCharsets.UTF_8) // RFC 5987
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, cd.toString())
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }
}