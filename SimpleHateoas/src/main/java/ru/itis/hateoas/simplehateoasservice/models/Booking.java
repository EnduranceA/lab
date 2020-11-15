package ru.itis.hateoas.simplehateoasservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne
    private Product product;

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
