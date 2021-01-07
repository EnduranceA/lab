package dto;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Serialization
public class UserDto {
    private String firstName;
    private String lastName;
    private String numberOfPassport;
    private Integer age;
    private String date;

}
