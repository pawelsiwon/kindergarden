package pl.kielce.tu.przedszkole.przedszkole.service.ClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.Class;
import pl.kielce.tu.przedszkole.przedszkole.repository.ClassRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public void addClass(String issuingUsername, Class addedClass) throws Exception {
        classRepository.save(addedClass);
    }

    @Override
    public void editClass(String issuingUsername, Class editedClass) throws Exception {
        Optional<Class> optionalClass = classRepository.findById(editedClass.getId());
        if(!optionalClass.isPresent()) {
            throw new Exception("Edited class does not exist!");
        }
        Class classBeingEdited = optionalClass.get();

        classBeingEdited.setName(editedClass.getName());
        classBeingEdited.setChilds(editedClass.getChilds());
        classBeingEdited.setYearStart(editedClass.getYearStart());
        classBeingEdited.setYearEnd(editedClass.getYearEnd());
        classBeingEdited.setPerson(classBeingEdited.getPerson());

        classRepository.save(classBeingEdited);
    }

    @Override
    public void deleteClass(String issuingUsername, Long deletedClassId) throws Exception{
        Optional<Class> optionalClass = classRepository.findById(deletedClassId);
        if(!optionalClass.isPresent()) {
            throw new Exception("Edited class does not exist!");
        }

        classRepository.delete(optionalClass.get());
    }

    @Override
    public List<Class> getClasses() {
        return classRepository.findAll();
    }

    @Override
    public Class getClassById(Long classId) {
        return classRepository.findById(classId).orElse(null);
    }
}
