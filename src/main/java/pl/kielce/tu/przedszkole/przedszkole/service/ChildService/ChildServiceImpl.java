package pl.kielce.tu.przedszkole.przedszkole.service.ChildService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.repository.ChildRepository;
import pl.kielce.tu.przedszkole.przedszkole.service.ChildService.ChildCommand.AddChildCommand;
import pl.kielce.tu.przedszkole.przedszkole.service.ChildService.ChildCommand.ChildCommand;
import pl.kielce.tu.przedszkole.przedszkole.service.ChildService.ChildCommand.DeleteChildCommand;
import pl.kielce.tu.przedszkole.przedszkole.service.ChildService.ChildCommand.EditChildCommand;

import java.util.List;
import java.util.Optional;

@Service
public class ChildServiceImpl implements ChildService{


    private final ChildRepository childRepository;
    private final ChildCommand addChildCommand;
    private final ChildCommand editChildCommand;
    private final ChildCommand deleteChildCommand;

    @Autowired
    public ChildServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
        addChildCommand = new AddChildCommand(childRepository);
        editChildCommand = new EditChildCommand(childRepository);
        deleteChildCommand = new DeleteChildCommand(childRepository);
    }

    @Override
    public void addChild(String issuerUsername, Child child) throws Exception {
        addChildCommand.execute(child);
    }

    @Override
    public void editChild(String issuerUsername, Child child) throws Exception {
        Optional<Child> childToEdit = childRepository.findById(child.getId());
        if(!childToEdit.isPresent())
        {
            throw new Exception("There is no child with that ID to edit!");
        }

        editChildCommand.execute(child);
    }

    @Override
    public void deleteChild(String issuerUsername, Long childId) throws Exception {
        Optional<Child> childToDelete = childRepository.findById(childId);
        if(!childToDelete.isPresent()) {
            throw new Exception("Child to delete not found!");
        }

        deleteChildCommand.execute(childToDelete.get());
    }

    @Override
    public List<Child> getChildren() {
        return childRepository.findAll();
    }

    @Override
    public Child getChildById(Long childId) {
        Optional<Child> childById = childRepository.findById(childId);

        return childById.orElse(null);
    }
}
