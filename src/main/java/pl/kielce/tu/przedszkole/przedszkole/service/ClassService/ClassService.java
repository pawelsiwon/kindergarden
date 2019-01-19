package pl.kielce.tu.przedszkole.przedszkole.service.ClassService;

import pl.kielce.tu.przedszkole.przedszkole.dto.ClassResponseDto;
import pl.kielce.tu.przedszkole.przedszkole.model.Classroom;

import java.util.List;

public interface ClassService {
    void addClass(String issuingUsername, Classroom addedClassroom) throws Exception;

    void editClass(String issuingUsername, Classroom editedClassroom) throws Exception;

    void deleteClass(String issuingUsername, Long deletedClassId) throws Exception;

    List<Classroom> getClasses();

    ClassResponseDto getClassById(Long classId);
}
