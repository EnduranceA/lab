package ru.itis.hateoas.simplehateoasservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.hateoas.simplehateoasservice.models.*;
import ru.itis.hateoas.simplehateoasservice.repositories.*;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootApplication
public class SimpleHateoasServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SimpleHateoasServiceApplication.class, args);

        BookingRepository bookingRepository = context.getBean(BookingRepository.class);
        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        SellerRepository sellerRepository = context.getBean(SellerRepository.class);
        ShopRepository shopRepository = context.getBean(ShopRepository.class);

        Customer customer = Customer.builder()
                .phoneNumber("89375776004")
                .email("elanskaya@mail.ru")
                .password("dvjo3kf54")
                .build();

        customerRepository.save(customer);

        Shop shop = Shop.builder()
                .name("shop1")
                .build();

        shopRepository.save(shop);

        Seller seller = Seller.builder()
                .email("vdv@inbox.ru")
                .password("uhghgorokg5")
                .shop(shop)
                .build();

        sellerRepository.save(seller);

        Product product = Product.builder()
                .title("product1")
                .price(24)
                .count(10)
                .seller(seller)
                .build();

        productRepository.save(product);

        Booking booking = Booking.builder()
                .customer(customer)
                .product(product)
                .state(State.ACCEPTED)
                .build();
        bookingRepository.save(booking);

    }

}
