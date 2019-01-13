package pl.kielce.tu.przedszkole.przedszkole.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.New;

import java.util.List;
import java.util.logging.Logger;

@Service
public class NewsServiceTeacherProxy implements NewsService {

    private final NewsServiceImpl newsServiceImpl;
    private final Logger logger = Logger.getLogger(NewsServiceTeacherProxy.class.getName());

    @Autowired
    public NewsServiceTeacherProxy(NewsServiceImpl newsServiceImpl) {
        this.newsServiceImpl = newsServiceImpl;
    }

    @Override
    public void addNews(String issuingUserName, New news) throws Exception {
        logger.info("Teacher"+issuingUserName+" added news piece with id:"+news.getId());
        newsServiceImpl.addNews(issuingUserName, news);
    }

    @Override
    public void editNews(String issuingUserName, New news) throws Exception {
        logger.info("Teacher"+issuingUserName+" edits(or at least attempts to edit) news piece with id:"+news.getId());
        newsServiceImpl.editNews(issuingUserName, news);
    }

    @Override
    public void deleteNews(String issuingUserName, Long newsId) throws Exception {
        logger.info("Teacher"+issuingUserName+" deletes(or at least attempts to delete news piece with id:"+newsId);
        newsServiceImpl.deleteNews(issuingUserName, newsId);
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
