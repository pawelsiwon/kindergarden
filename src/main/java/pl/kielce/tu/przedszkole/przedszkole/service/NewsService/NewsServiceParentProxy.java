package pl.kielce.tu.przedszkole.przedszkole.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.New;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class NewsServiceParentProxy implements NewsService {

    private final Logger logger = Logger.getLogger(NewsServiceParentProxy.class.getName());
    private final NewsServiceImpl newsServiceImpl;

    @Autowired
    public NewsServiceParentProxy(NewsServiceImpl newsServiceImpl) {
        this.newsServiceImpl = newsServiceImpl;
    }


    @Override
    public void addNews(String issuingUserName, New news) throws Exception {
        logger.warning("Parent "+issuingUserName+" attempted to add news.");
        throw new OperationNotSupportedException("Cannot add news through this proxy!");
    }

    @Override
    public void editNews(String issuingUserName, New news) throws Exception {
        logger.warning("Parent "+issuingUserName+" attempted to add news.");
        throw new OperationNotSupportedException("Cannot add news through this proxy!");
    }

    @Override
    public void deleteNews(String issuingUserName, Long newsId) throws Exception {
        logger.warning("Parent "+issuingUserName+" attempted to add news.");
        throw new OperationNotSupportedException("Cannot add news through this proxy!");
    }

    @Override
    public List<New> getNewsList() {
        return newsServiceImpl.getNewsList();
    }

    @Override
    public New getNewsById(Long newsId) {
        return newsServiceImpl.getNewsById(newsId);
    }
}
