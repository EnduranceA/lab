package ru.itis.mongodb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    private String _id;
    private String customerId;
    private List<Product> products;
    private State state;
    private Integer sum;
    private String comment;

    //оформление заказ (изменить статус -> "REGISTERED")
    public void register() {
        this.state = State.REGISTERED;
    }

    //отправка заказа (изменить статус -> "SENT")
    public void send() {
        //проверяем, что статус был REGISTERED
        if (this.state.equals(State.REGISTERED)) {
            //заменяем на статус SENT
            this.state = State.SENT;
        }
        else if (this.state.equals(State.RECEIVED)){
            throw new IllegalArgumentException();
        }
    }

    public void receive() {
        //проверяем, что статус был REGISTERED
        if (this.state.equals(State.SENT)) {
            //заменяем на статус SENT
            this.state = State.RECEIVED;
        }
        else if (this.state.equals(State.RECEIVED)){
            throw new IllegalArgumentException();
        }
    }
}