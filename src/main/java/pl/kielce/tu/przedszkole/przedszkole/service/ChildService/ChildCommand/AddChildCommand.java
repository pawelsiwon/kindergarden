package pl.kielce.tu.przedszkole.przedszkole.service.ChildService.ChildCommand;

import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.repository.ChildRepository;

public class AddChildCommand implements ChildCommand {
    private final ChildRepository childRepository;


    public AddChildCommand(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public void execute(Child child) {
        childRepository.save(child);
    }
}
