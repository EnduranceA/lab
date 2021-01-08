package ru.itis.mongodb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.mongodb.models.State;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private String customerId;
    private List<String> productNames;
    private State state;
    private Integer sum;
    private String comment;
}
