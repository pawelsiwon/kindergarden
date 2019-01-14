package pl.kielce.tu.przedszkole.przedszkole.service.ClassService;

import pl.kielce.tu.przedszkole.przedszkole.model.Class;

import java.util.List;

public interface ClassService {
    void addClass(String issuingUsername, Class addedClass) throws Exception;

    void editClass(String issuingUsername, Class editedClass) throws Exception;

    void deleteClass(String issuingUsername, Long deletedClassId) throws Exception;

    List<Class> getClasses();

    Class getClassById(Long classId);
}
