package pl.kielce.tu.przedszkole.przedszkole.service.ClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.Class;
import pl.kielce.tu.przedszkole.przedszkole.repository.ClassRepository;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ClassServiceParentProxy implements ClassService{
    private static final Logger logger = Logger.getLogger(ClassServiceParentProxy.class.getName());
    private final ClassServiceImpl classServiceImpl;

    @Autowired
    public ClassServiceParentProxy(ClassServiceImpl classServiceImpl) {
        this.classServiceImpl = classServiceImpl;
    }

    @Override
    public void addClass(String issuingUsername, Class addedClass) throws Exception {
        logger.warning(issuingUsername+" attempted to add class.");
        throw new OperationNotSupportedException("User is not permitted to add classes.");
    }

    @Override
    public void editClass(String issuingUsername, Class editedClass) throws Exception {
        logger.warning(issuingUsername+" attempted to edit class.");
        throw new OperationNotSupportedException("User is not permitted to edit classes.");
    }

    @Override
    public void deleteClass(String issuingUsername, Long deletedClassId) throws Exception {
        logger.warning(issuingUsername+" attempted to delete class.");
        throw new OperationNotSupportedException("User is not permitted to delete classes.");
    }

    @Override
    public List<Class> getClasses() {
        return classServiceImpl.getClasses();
    }

    @Override
    public Class getClassById(Long classId) {
        return classServiceImpl.getClassById(classId);
    }
}
