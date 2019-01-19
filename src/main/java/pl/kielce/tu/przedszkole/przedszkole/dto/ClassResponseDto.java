package pl.kielce.tu.przedszkole.przedszkole.dto;

import lombok.Data;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.model.Classroom;

import java.util.List;

@Data
public class ClassResponseDto {
    private Classroom classroom;

    private List<Child> childList;
}
