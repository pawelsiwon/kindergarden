package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.service.ChildService.ChildService;
import pl.kielce.tu.przedszkole.przedszkole.service.ChildService.ChildServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.ChildService.ChildServiceParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.ChildService.ChildServiceTeacherProxy;
import pl.kielce.tu.przedszkole.przedszkole.utils.PersonResolverUtils;

import java.util.List;

@Service
public class ChildProxyDispatcher {

    private final PersonResolverUtils personResolverUtils;
    private final ChildServiceImpl childServiceImpl;
    private final ChildServiceTeacherProxy childServiceTeacherProxy;
    private final ChildServiceParentProxy childServiceParentProxy;

    @Autowired
    public ChildProxyDispatcher(PersonResolverUtils personResolverUtils,
                                ChildServiceImpl childServiceImpl,
                                ChildServiceTeacherProxy childServiceTeacherProxy,
                                ChildServiceParentProxy childServiceParentProxy) {
        this.personResolverUtils = personResolverUtils;
        this.childServiceImpl = childServiceImpl;
        this.childServiceTeacherProxy = childServiceTeacherProxy;
        this.childServiceParentProxy = childServiceParentProxy;
    }

    private ChildService resolveChildInterface(Person person) {
        if(person.getRole().equalsIgnoreCase("ADMIN")) return childServiceImpl;
        else if(person.getRole().equalsIgnoreCase("TEACHER")) return childServiceTeacherProxy;
        else if(person.getRole().equalsIgnoreCase("PARENT")) return childServiceParentProxy;
        else return null;
    }

    @Transactional
    public void addChild(String issuingPersonUsername, Child child) throws Exception {
        Person issuingPerson = personResolverUtils.resolvePersonByLogin(issuingPersonUsername);

        resolveChildInterface(issuingPerson).addChild(issuingPersonUsername, child);
    }

    @Transactional
    public void editChild(String issuingPersonUsername, Child child) throws Exception {
        Person issuingPerson = personResolverUtils.resolvePersonByLogin(issuingPersonUsername);
        resolveChildInterface(issuingPerson).editChild(issuingPersonUsername, child);
    }

    @Transactional
    public void deleteChild(String issuingPersonUsername, Long childId) throws Exception {
        Person issuingPerson = personResolverUtils.resolvePersonByLogin(issuingPersonUsername);
        resolveChildInterface(issuingPerson).deleteChild(issuingPersonUsername, childId);
    }

    public List<Child> getChildren(String issuingPersonUsername) throws Exception {
        Person issuingPerson = personResolverUtils.resolvePersonByLogin(issuingPersonUsername);
        return resolveChildInterface(issuingPerson).getChildren();
    }

    public Child getChildById(String issuingPersonUsername, Long childId) {
        return resolveChildInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)).getChildById(childId);
    }
}
