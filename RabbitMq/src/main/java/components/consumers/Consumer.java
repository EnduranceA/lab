package components.consumers;

import com.rabbitmq.client.ConnectionFactory;
import helpers.JsonHelper;
import helpers.PdfGenerationHelper;

public abstract class Consumer {

    // есть exchange "documents"
    public final static String EXCHANGE_NAME = "documents";
    // тип exchange - fanout
    public final static String EXCHANGE_TYPE = "fanout";

    protected ConnectionFactory connectionFactory;
    protected JsonHelper jsonHelper;
    protected PdfGenerationHelper pdfGenerationHelper;

    public Consumer() {
        this.connectionFactory = new ConnectionFactory();
        this.jsonHelper = new JsonHelper();
        this.pdfGenerationHelper = new PdfGenerationHelper();
    }

    public abstract void consume();
}
