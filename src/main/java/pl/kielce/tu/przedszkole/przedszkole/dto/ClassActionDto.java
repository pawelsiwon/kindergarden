package pl.kielce.tu.przedszkole.przedszkole.dto;

import lombok.Data;
import pl.kielce.tu.przedszkole.przedszkole.model.Classroom;

@Data
public class ClassActionDto {
    private LoginData loginData;

    private Classroom transferedClassroom;

    private Long classId;
}
