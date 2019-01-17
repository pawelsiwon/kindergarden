package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.przedszkole.przedszkole.model.New;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsService;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsServiceParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsServiceTeacherProxy;
import pl.kielce.tu.przedszkole.przedszkole.utils.PersonResolverUtils;

import java.util.List;
import java.util.Objects;

@Service
public class NewsProxyDispatcher {

    private final PersonResolverUtils personResolverUtils;
    private final NewsServiceImpl newsServiceImpl;
    private final NewsServiceTeacherProxy newsServiceTeacherProxy;
    private final NewsServiceParentProxy newsServiceParentProxy;

    @Autowired
    public NewsProxyDispatcher(PersonResolverUtils personResolverUtils,
                               NewsServiceImpl newsServiceImpl,
                               NewsServiceTeacherProxy newsServiceTeacherProxy,
                               NewsServiceParentProxy newsServiceParentProxy) {
        this.personResolverUtils = personResolverUtils;
        this.newsServiceImpl = newsServiceImpl;
        this.newsServiceTeacherProxy = newsServiceTeacherProxy;
        this.newsServiceParentProxy = newsServiceParentProxy;
    }

    private NewsService resolveNewsInterface(Person person) {
        if(person.getRole().equalsIgnoreCase("ADMIN")) return newsServiceImpl;
        else if(person.getRole().equalsIgnoreCase("TEACHER")) return newsServiceTeacherProxy;
        else if(person.getRole().equalsIgnoreCase("PARENT")) return newsServiceParentProxy;
        else return null;
    }

    @Transactional
    public void addNews(String issuingPersonUsername, New news) throws Exception {
        Objects.requireNonNull(resolveNewsInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)))
                .addNews(issuingPersonUsername, news);
    }

    @Transactional
    public void editNews(String issuingPersonUsername, New news) throws Exception {
        Objects.requireNonNull(resolveNewsInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)))
                .editNews(issuingPersonUsername, news);
    }

    @Transactional
    public void deleteNews(String issuingPersonUsername, Long newsId) throws Exception {
        Objects.requireNonNull(resolveNewsInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)))
                .deleteNews(issuingPersonUsername, newsId);
    }

    public List<New> getNewsList(String issuingPersonUsername) {
        return Objects.requireNonNull(resolveNewsInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)))
                .getNewsList();
    }

    public New getNewsById(String issuingPersonUsername, Long newsId) {
        return Objects.requireNonNull(resolveNewsInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)))
                .getNewsById(newsId);
    }
}
