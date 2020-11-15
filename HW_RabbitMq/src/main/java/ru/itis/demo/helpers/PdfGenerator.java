package ru.itis.demo.helpers;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class PdfGenerator {

    @Autowired
    private HtmlGenerator htmlGenerator;

    public void createPdf(String pathName, String template, Object user) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pathName));
            document.open();
            document.add(new Chunk(""));
            String htmlStr = htmlGenerator.generate(template, user);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                    new ByteArrayInputStream(htmlStr.getBytes()),
                    null, StandardCharsets.UTF_8);
            document.close();
        } catch (DocumentException | IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
