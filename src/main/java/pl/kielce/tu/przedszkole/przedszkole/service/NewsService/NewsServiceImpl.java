package pl.kielce.tu.przedszkole.przedszkole.service.NewsService;

import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.New;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.NewsRepository;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    private final PersonRepository personRepository;
    private final NewsRepository newsRepository;

    public NewsServiceImpl(PersonRepository personRepository, NewsRepository newsRepository) {
        this.personRepository = personRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public void addNews(String issuingUserName, New news) throws Exception{
        Optional<Person> person = personRepository.findByLogin(issuingUserName);
        if(!person.isPresent()) {
            throw new Exception("Person does not exist!");
        }
        Person author = person.get();
        news.setPerson(author);




        news.setCreatedDate(new Date());
        newsRepository.save(news);
    }

    @Override
    public void editNews(String issuingUserName, New news) throws Exception{
        Optional<New> newsToEdit = newsRepository.findById(news.getId());
        if(!newsToEdit.isPresent()) {
            throw new Exception("There is no such news to edit!");
        }
        Optional<Person> editorOptional = personRepository.findByLogin(issuingUserName);
        if(!editorOptional.isPresent()) {
            throw new Exception("There is no such person!");
        }
        New newsBeingEdited = newsToEdit.get();
        Person editor = editorOptional.get();
        if(!editor.getLogin().equals(newsBeingEdited.getPerson().getLogin()) &&
                !editor.getRole().equalsIgnoreCase("ADMIN")) {
            throw new Exception("User is not authorized to edit that news piece.");
        }


        newsBeingEdited.setContetnt(news.getContetnt());
        newsBeingEdited.setTitle(news.getTitle());
        newsRepository.save(newsBeingEdited);
    }

    @Override
    public void deleteNews(String issuingUserName, Long newsId) throws Exception {
        Optional<New> newsToDelete = newsRepository.findById(newsId);
        Optional<Person> editorOptional = personRepository.findByLogin(issuingUserName);
        if(!editorOptional.isPresent()) {
            throw new Exception("There is no such person!");
        }
        if(!newsToDelete.isPresent()) {
            throw new Exception("News piece to delete does not exist!");
        }
        New newsBeingDeleted = newsToDelete.get();
        Person editor = editorOptional.get();
        if(!editor.getLogin().equals(newsBeingDeleted.getPerson().getLogin()) &&
                !editor.getRole().equalsIgnoreCase("ADMIN")) {
            throw new Exception("User is not authorized to edit that news piece.");
        }

        newsRepository.delete(newsBeingDeleted);
    }

    @Override
    public List<New> getNewsList() {
        return newsRepository.findAll()
                .stream()
                .filter(news -> news.getCreatedDate() != null)
                .sorted(Comparator.comparing(New::getCreatedDate).thenComparing(New::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public New getNewsById(Long newsId) {
        Optional<New> newsToShow = newsRepository.findById(newsId);
        return newsToShow.orElse(null);
    }
}
