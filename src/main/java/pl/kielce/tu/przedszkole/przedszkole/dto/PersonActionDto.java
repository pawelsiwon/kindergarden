package pl.kielce.tu.przedszkole.przedszkole.dto;

import lombok.Data;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;

@Data
public class PersonActionDto {
    private LoginData loginData;

    Person person;

    Long personId;
}
