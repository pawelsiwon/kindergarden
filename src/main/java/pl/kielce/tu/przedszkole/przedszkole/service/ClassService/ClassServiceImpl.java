package pl.kielce.tu.przedszkole.przedszkole.service.ClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.dto.ClassResponseDto;
import pl.kielce.tu.przedszkole.przedszkole.model.Classroom;
import pl.kielce.tu.przedszkole.przedszkole.repository.ChildRepository;
import pl.kielce.tu.przedszkole.przedszkole.repository.ClassRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final ChildRepository childRepository;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository, ChildRepository childRepository) {
        this.classRepository = classRepository;
        this.childRepository = childRepository;
    }

    @Override
    public void addClass(String issuingUsername, Classroom addedClassroom) throws Exception {
        classRepository.save(addedClassroom);
    }

    @Override
    public void editClass(String issuingUsername, Classroom editedClassroom) throws Exception {
        Optional<Classroom> optionalClass = classRepository.findById(editedClassroom.getId());
        if(!optionalClass.isPresent()) {
            throw new Exception("Edited class does not exist!");
        }
        Classroom classroomBeingEdited = optionalClass.get();

        classroomBeingEdited.setName(editedClassroom.getName());
        classroomBeingEdited.setYearStart(editedClassroom.getYearStart());
        classroomBeingEdited.setYearEnd(editedClassroom.getYearEnd());
        classroomBeingEdited.setPerson(classroomBeingEdited.getPerson());

        classRepository.save(classroomBeingEdited);
    }

    @Override
    public void deleteClass(String issuingUsername, Long deletedClassId) throws Exception{
        Optional<Classroom> optionalClass = classRepository.findById(deletedClassId);
        if(!optionalClass.isPresent()) {
            throw new Exception("Edited class does not exist!");
        }

        classRepository.delete(optionalClass.get());
    }

    @Override
    public List<Classroom> getClasses() {
        return classRepository.findAll();
    }

    @Override
    public ClassResponseDto getClassById(Long classId) {
        ClassResponseDto classResponseDto = new ClassResponseDto();
        classResponseDto.setClassroom(classRepository.findById(classId).orElse(null));
        classResponseDto.setChildList(childRepository.findByClazzId(classId));
        //return classRepository.findById(classId).orElse(null);
        return classResponseDto;
    }
}
