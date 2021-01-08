package ru.itis.mongodb.repositories.driver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.codecs.ObjectIdGenerator;
import org.bson.types.ObjectId;
import ru.itis.mongodb.models.Booking;
import ru.itis.mongodb.models.Product;
import ru.itis.mongodb.models.State;
import ru.itis.mongodb.repositories.BookingRepository;

public class BookingRepositoryMongoDriverImpl implements BookingRepository {

    private final MongoCollection<Document> collection;
    private final ObjectMapper objectMapper;

    public BookingRepositoryMongoDriverImpl() {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("online_store");
        collection = database.getCollection("bookings");
        objectMapper = new ObjectMapper();
    }

    public Booking findById(String _id) {
        Document document = collection.find(Filters.eq("_id", _id)).first();
        Booking booking = new Booking();
        if (document != null) {
            booking = Booking.builder()
                    ._id(document.getString("_id"))
                    .comment(document.getString("comments"))
                    .customerId(document.getString("customer_id"))
                    .products(document.getList(Product.builder(), Product.class))
                    .state(document.get("state", State.class))
                    .sum(document.getInteger("sum"))
                    .build();
        }
        return booking;
    }

    @Override
    public void save(Booking booking) {
        Document bookingFromDocument = null;
        try {
            bookingFromDocument = Document.parse(objectMapper.writeValueAsString(booking));
            if (bookingFromDocument.getObjectId("_id") == null) {
                bookingFromDocument.put("_id", new ObjectIdGenerator().generate());
            }
            collection.insertOne(bookingFromDocument);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(String id) {
        //ObjectId - это BSON type
        collection.deleteOne(Filters.eq(new ObjectId(id)));
    }

    @Override
    public void update(Booking booking) {
        collection.updateOne(Filters.eq("_id", booking.get_id()), Updates.set("customer_id", booking.getCustomerId()));
        collection.updateOne(Filters.eq("_id", booking.get_id()), Updates.set("products", booking.getProducts()));
        collection.updateOne(Filters.eq("_id", booking.get_id()), Updates.set("state", booking.getState()));
        collection.updateOne(Filters.eq("_id", booking.get_id()), Updates.set("comment", booking.getComment()));
        collection.updateOne(Filters.eq("_id", booking.get_id()), Updates.set("sum", booking.getSum()));
    }
}
