package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.przedszkole.przedszkole.model.Class;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.service.ClassService.ClassService;
import pl.kielce.tu.przedszkole.przedszkole.service.ClassService.ClassServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.ClassService.ClassServiceParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.ClassService.ClassServiceTeacherProxy;
import pl.kielce.tu.przedszkole.przedszkole.utils.PersonResolverUtils;

import java.util.List;

@Service
public class ClassProxyDispatcher {
    private final PersonResolverUtils personResolverUtils;
    private final ClassServiceImpl classServiceImpl;
    private final ClassServiceTeacherProxy classServiceTeacherProxy;
    private final ClassServiceParentProxy classServiceParentProxy;

    @Autowired
    public ClassProxyDispatcher(PersonResolverUtils personResolverUtils,
                                ClassServiceImpl classServiceImpl,
                                ClassServiceTeacherProxy classServiceTeacherProxy,
                                ClassServiceParentProxy classServiceParentProxy) {
        this.personResolverUtils = personResolverUtils;
        this.classServiceImpl = classServiceImpl;
        this.classServiceTeacherProxy = classServiceTeacherProxy;
        this.classServiceParentProxy = classServiceParentProxy;
    }

    private ClassService resolveClassInterface(Person person) {
        if(person.getRole().equalsIgnoreCase("ADMIN")) return classServiceImpl;
        else if(person.getRole().equalsIgnoreCase("TEACHER")) return classServiceTeacherProxy;
        else if(person.getRole().equalsIgnoreCase("PARENT")) return classServiceParentProxy;
        else return null;
    }

    @Transactional
    public void addClass(String issuingPersonUsername, Class addedClass) throws Exception {
        resolveClassInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)).addClass(issuingPersonUsername, addedClass);
    }

    @Transactional
    public void editClass(String issuingPersonUsername, Class editedClass) throws Exception {
        resolveClassInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)).editClass(issuingPersonUsername, editedClass);
    }

    @Transactional
    public void deleteClass(String issuingPersonUsername, Long classId) throws Exception {
        resolveClassInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)).deleteClass(issuingPersonUsername, classId);
    }

    public List<Class> getClasses(String issuingPersonUsername) {
        return resolveClassInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)).getClasses();
    }

    public Class getClassById(String issuingPersonUsername, Long classId) {
        return resolveClassInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)).getClassById(classId);
    }
}
