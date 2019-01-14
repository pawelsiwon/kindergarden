package pl.kielce.tu.przedszkole.przedszkole.dto;

import lombok.Data;
import pl.kielce.tu.przedszkole.przedszkole.model.Class;

@Data
public class ClassActionDto {
    private LoginData loginData;

    private Class transferedClass;

    private Long classId;
}
