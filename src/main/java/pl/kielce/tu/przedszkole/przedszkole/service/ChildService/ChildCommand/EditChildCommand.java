package pl.kielce.tu.przedszkole.przedszkole.service.ChildService.ChildCommand;

import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.repository.ChildRepository;

public class EditChildCommand implements ChildCommand {
    private final ChildRepository childRepository;

    public EditChildCommand(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public void execute(Child child) {
        Child childToEdit = childRepository.getOne(child.getId());
        childToEdit.setName(child.getName());
        childToEdit.setSurname(child.getSurname());
        childToEdit.setCity(child.getCity());
        childToEdit.setStreet(child.getCity());
        childToEdit.setStreetAddress(child.getStreetAddress());
        childToEdit.setPostale(child.getPostale());
        childToEdit.setAdmissionDate(child.getAdmissionDate());
        childToEdit.setBirthdate(child.getBirthdate());
        childToEdit.setPeople(child.getPeople());

        childRepository.save(childToEdit);
    }
}
