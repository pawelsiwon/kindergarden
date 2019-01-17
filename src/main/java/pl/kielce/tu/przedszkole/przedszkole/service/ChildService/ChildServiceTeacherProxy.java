package pl.kielce.tu.przedszkole.przedszkole.service.ChildService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ChildServiceTeacherProxy implements ChildService {

    private final Logger logger = Logger.getLogger(ChildServiceTeacherProxy.class.getName());
    private final ChildServiceImpl childServiceImpl;

    @Autowired
    public ChildServiceTeacherProxy(ChildServiceImpl childServiceImpl) {
        this.childServiceImpl = childServiceImpl;
    }

    @Override
    public void addChild(String issuerUsername, Child child) throws Exception {
        logger.warning("User " + issuerUsername + " attempted to add child through proxy");
        throw new OperationNotSupportedException("Not possible through proxy");
    }

    @Override
    public void editChild(String issuerUsername, Child child) throws Exception {
        logger.warning("User " + issuerUsername + " attempted to edit child through proxy");
        throw new OperationNotSupportedException("Not possible through proxy");
    }

    @Override
    public void deleteChild(String issuerUsername, Long childId) throws Exception {
        logger.warning("User " + issuerUsername + " attempted to delete child through proxy");
        throw new OperationNotSupportedException("Not possible through proxy");
    }

    @Override
    public List<Child> getChildren() {
        return childServiceImpl.getChildren();
    }

    @Override
    public Child getChildById(Long childId) {
        return childServiceImpl.getChildById(childId);
    }
}
