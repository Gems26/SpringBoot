package com.example.pdfdemo.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
// import org.apache.pdfbox.pdmodel.font.PDType0Font; // <- use for Unicode
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    public byte[] generatePdf(String text) throws IOException {
        if (text == null || text.isBlank()) {
            text = "No content provided.";
        }

        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PDRectangle pageSize = PDRectangle.A4;
            float margin = 50f;
            float width = pageSize.getWidth() - 2 * margin;
            float startY = pageSize.getHeight() - margin;
            float fontSize = 12f;
            float leading = 1.4f * fontSize;

            // Use Helvetica (Latin only)
            var font = PDType1Font.HELVETICA;

            // If you need Unicode (Tamil, etc.), use a TTF:
            // var font = PDType0Font.load(document, new File("fonts/NotoSans-Regular.ttf"));

            List<String> lines = wrapText(text, font, fontSize, width);

            int lineIndex = 0;
            while (lineIndex < lines.size()) {
                PDPage page = new PDPage(pageSize);
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.beginText();
                    contentStream.setLeading(leading);
                    contentStream.setFont(font, fontSize);
                    contentStream.newLineAtOffset(margin, startY);

                    float y = startY;
                    while (lineIndex < lines.size()) {
                        if (y - leading < margin) {
                            break; // next page
                        }
                        String line = lines.get(lineIndex);

                        // If using PDType0Font, consider checking encodability:
                        // for (char ch : line.toCharArray()) {
                        //     if (!font.willEncode(ch)) {
                        //         // replace or handle fallback
                        //     }
                        // }

                        contentStream.showText(line);
                        contentStream.newLineAtOffset(0, -leading);
                        y -= leading;
                        lineIndex++;
                    }
                    contentStream.endText();
                }
            }

            document.save(baos);
            return baos.toByteArray();
        }
    }

    private List<String> wrapText(String text, org.apache.pdfbox.pdmodel.font.PDFont font, float fontSize, float maxWidth) throws IOException {
        List<String> wrapped = new ArrayList<>();

        // Normalize newlines
        String normalized = text.replace("\r\n", "\n");

        // Split paragraphs by blank lines; for line-wise wrap use: normalized.split("\\n")
        String[] paragraphs = normalized.split("\\n\\s*\\n");

        for (String paragraph : paragraphs) {
            // Keep inner line breaks within a paragraph
            String[] linesInPara = paragraph.split("\\n");

            for (String rawLine : linesInPara) {
                String line = rawLine.trim();
                if (line.isEmpty()) {
                    wrapped.add(""); // Preserve blank line
                    continue;
                }

                String[] words = line.split("\\s+"); // fixed: escaped backslash
                StringBuilder current = new StringBuilder();

                for (String word : words) {
                    String testLine = current.length() == 0 ? word : current + " " + word;
                    float size = getStringWidth(font, testLine, fontSize);
                    if (size <= maxWidth) {
                        current.setLength(0);
                        current.append(testLine);
                    } else {
                        if (current.length() > 0) {
                            wrapped.add(current.toString());
                        }
                        // If the single word is too long, hard-break it
                        if (getStringWidth(font, word, fontSize) > maxWidth) {
                            wrapped.addAll(hardBreakWord(word, font, fontSize, maxWidth));
                            current.setLength(0);
                        } else {
                            current.setLength(0);
                            current.append(word);
                        }
                    }
                }
                if (current.length() > 0) {
                    wrapped.add(current.toString());
                }
            }

            // Add a blank line between paragraphs
            wrapped.add("");
        }

        // Remove trailing blank line if added
        if (!wrapped.isEmpty() && wrapped.get(wrapped.size() - 1).isEmpty()) {
            wrapped.remove(wrapped.size() - 1);
        }

        return wrapped;
    }

    private List<String> hardBreakWord(String word, org.apache.pdfbox.pdmodel.font.PDFont font, float fontSize, float maxWidth) throws IOException {
        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            String test = current.toString() + c;
            float size = getStringWidth(font, test, fontSize);
            if (size <= maxWidth) {
                current.append(c);
            } else {
                if (current.length() > 0) {
                    parts.add(current.toString());
                }
                current.setLength(0);
                current.append(c);
            }
        }

        if (current.length() > 0) {
            parts.add(current.toString());
        }
        return parts;
    }

    private float getStringWidth(org.apache.pdfbox.pdmodel.font.PDFont font, String text, float fontSize) throws IOException {
        return font.getStringWidth(text) / 1000f * fontSize;
    }
}