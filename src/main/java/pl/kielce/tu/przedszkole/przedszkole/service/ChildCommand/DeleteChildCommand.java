package pl.kielce.tu.przedszkole.przedszkole.service.ChildCommand;

import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.repository.ChildRepository;

public class DeleteChildCommand implements ChildCommand {

    private final ChildRepository childRepository;

    public DeleteChildCommand(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public void execute(Child child) {
        childRepository.delete(child);
    }
}
