package pl.kielce.tu.przedszkole.przedszkole.service.ClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.dto.ClassResponseDto;
import pl.kielce.tu.przedszkole.przedszkole.model.Classroom;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ClassServiceTeacherProxy implements ClassService {
    private static final Logger logger = Logger.getLogger(ClassServiceTeacherProxy.class.getName());

    private final ClassServiceImpl classServiceImpl;

    @Autowired
    public ClassServiceTeacherProxy(ClassServiceImpl classServiceImpl) {
        this.classServiceImpl = classServiceImpl;
    }

    @Override
    public void addClass(String issuingUsername, Classroom addedClassroom) throws Exception {
        logger.warning(issuingUsername+" attempted to add class.");
        throw new OperationNotSupportedException("User is not permitted to add classes.");
    }

    @Override
    public void editClass(String issuingUsername, Classroom editedClassroom) throws Exception {
        classServiceImpl.editClass(issuingUsername, editedClassroom);
    }

    @Override
    public void deleteClass(String issuingUsername, Long deletedClassId) throws Exception {
        logger.warning(issuingUsername+" attempted to delete class.");
        throw new OperationNotSupportedException("User is not permitted to delete classes.");
    }

    @Override
    public List<Classroom> getClasses() {
        return classServiceImpl.getClasses();
    }

    @Override
    public ClassResponseDto getClassById(Long classId) {
        return classServiceImpl.getClassById(classId);
    }
}
