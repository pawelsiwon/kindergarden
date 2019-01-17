package pl.kielce.tu.przedszkole.przedszkole.service.ChildService;

import pl.kielce.tu.przedszkole.przedszkole.model.Child;

import java.util.List;

public interface ChildService {
    void addChild(String issuerUsername, Child child) throws Exception;

    void editChild(String issuerUsername, Child child) throws Exception;

    void deleteChild(String issuerUsername, Long childId) throws Exception;

    List<Child> getChildren();

    Child getChildById(Long childId);
}
