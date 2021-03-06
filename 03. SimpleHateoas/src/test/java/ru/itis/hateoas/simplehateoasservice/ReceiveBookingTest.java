package ru.itis.hateoas.simplehateoasservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.simplehateoasservice.models.Booking;
import ru.itis.hateoas.simplehateoasservice.models.Customer;
import ru.itis.hateoas.simplehateoasservice.models.Product;
import ru.itis.hateoas.simplehateoasservice.models.State;
import ru.itis.hateoas.simplehateoasservice.services.BookingService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ReceiveBookingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;
    @BeforeEach
    public void setUp() {
        when(bookingService.receive(1L)).thenReturn(receivedBooking());
    }

    @Test
    public void receiveBookingTest() {
        try {
            mockMvc.perform(put("/booking/1/receive"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.customer.email").value(receivedBooking().getCustomer().getEmail()))
                    .andExpect(jsonPath("$.product.title").value(receivedBooking().getProduct().getTitle()))
                    .andExpect(jsonPath("$.state").value(receivedBooking().getState().toString()))
                    .andDo(document("receive_booking", responseFields(
                            fieldWithPath("id").description("Идентификатор заказа"),
                            subsectionWithPath("customer").description("Информация о покупателе"),
                            subsectionWithPath("product").description("Товар в заказе"),
                            fieldWithPath("state").description("Статус заказа"))));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }


    private Booking receivedBooking() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("89375776004")
                .email("elanskaya@mail.ru")
                .password("dvjo3kf54")
                .build();
        Product product = Product.builder()
                .id(1L)
                .title("product1")
                .price(24)
                .count(10)
                .build();
        return Booking.builder()
                .id(1L)
                .customer(customer)
                .product(product)
                .state(State.RECEIVED)
                .build();
    }
}
