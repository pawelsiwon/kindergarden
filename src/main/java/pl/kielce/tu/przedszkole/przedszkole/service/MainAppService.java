package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.model.Class;
import pl.kielce.tu.przedszkole.przedszkole.model.New;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;
import pl.kielce.tu.przedszkole.przedszkole.service.ClassService.ClassService;
import pl.kielce.tu.przedszkole.przedszkole.service.ClassService.ClassServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.ClassService.ClassServiceParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.ClassService.ClassServiceTeacherProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsService;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsServiceParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsServiceTeacherProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonService;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonServiceParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonServiceTeacherProxy;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MainAppService {

}
