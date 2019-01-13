package pl.kielce.tu.przedszkole.przedszkole.dto;

import lombok.Data;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;

@Data
public class ChildActionDto {
    private LoginData loginData;

    Child child;

    Long childId;
}
