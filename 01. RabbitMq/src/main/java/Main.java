import com.rabbitmq.client.ConnectionFactory;
import factories.ConsumerFactory;
import components.producers.DocumentsProducer;

public class Main {

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        ConsumerFactory.getDismissalPdfConsumer().consume();
        ConsumerFactory.getDeductionPdfConsumer().consume();
        new DocumentsProducer(factory);
    }
}
