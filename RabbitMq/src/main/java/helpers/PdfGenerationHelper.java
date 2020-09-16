package helpers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfGenerationHelper {

    public void createDismissalPdf(JSONObject object) {
        try {
            Document document = new Document();
            String nameOfPdf = object.get("lastName") + "_dismissal_document.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(new File(nameOfPdf)));
            document.open();
            createAuthor(object, document);
            createTitle("Dismissal letter", document);
            Paragraph text = new Paragraph("\n" +
                    "“In accordance with article 80 of the Labor Code of the Russian Federation, " +
                    "I ask you to dismiss me of my own free will on February 22, 2020”.");
            document.add(text);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void createDeductionPdf(JSONObject object) {
        try {
            Document document = new Document();
            String nameOfPdf = object.get("lastName") + "_deduction_document_"  + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(new File(nameOfPdf)));
            document.open();
            createAuthor(object, document);
            createTitle("Deduction statement", document);
            Paragraph text = new Paragraph("I ask to be expelled from the university of my own free will\n");
            document.add(text);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void createAuthor(JSONObject object, Document document) {
        try {
            Paragraph data = new Paragraph("Created by: " + object.get("lastName") + " " + object.get("firstName") );
            data.setAlignment(Element.ALIGN_RIGHT);
            document.add(data);
        } catch (DocumentException e) {
          throw new IllegalArgumentException(e);
        }
    }

    public void createTitle(String name, Document document) {
        try {
            Paragraph title = new Paragraph(name);
            // отцентрированный параграф
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
        } catch (DocumentException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
