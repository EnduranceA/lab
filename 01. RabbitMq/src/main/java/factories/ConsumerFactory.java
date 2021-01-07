package factories;

import components.consumers.Consumer;
import components.consumers.DeductionPdfConsumer;
import components.consumers.DismissalPdfConsumer;


public class ConsumerFactory {

    public static Consumer getDismissalPdfConsumer() {
        return new DismissalPdfConsumer();
    }

    public static Consumer getDeductionPdfConsumer() {
        return new DeductionPdfConsumer();
    }
}
