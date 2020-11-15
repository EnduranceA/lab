package ru.itis.hateoas.simplehateoasservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.simplehateoasservice.controllers.BookingController;
import ru.itis.hateoas.simplehateoasservice.models.Booking;
import ru.itis.hateoas.simplehateoasservice.models.State;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookingRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Booking>> {

    @Autowired
    private RepositoryEntityLinks entityLinks;

    @Override
    public EntityModel<Booking> process(EntityModel<Booking> model) {
        //достаем заказ
        Booking booking = model.getContent();
        if (booking != null) {
            if (booking.getState().equals(State.REGISTERED)) {
                model.add(linkTo(methodOn(BookingController.class)
                        .send(booking.getId())).withRel("send"));
                model.add(entityLinks.linkToItemResource(Booking.class, booking.getId()).
                        withRel("receive"));
            }
            else if (booking.getState().equals(State.SENT)) {
                model.add(linkTo(methodOn(BookingController.class)
                        .receive(booking.getId())).withRel("receive"));
                model.add(entityLinks.linkToItemResource(Booking.class, booking.getId()).
                        withRel("delete"));
            }
        }
        return model;
    }
}
