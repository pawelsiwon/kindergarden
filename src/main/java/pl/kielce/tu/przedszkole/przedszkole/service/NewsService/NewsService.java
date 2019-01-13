package pl.kielce.tu.przedszkole.przedszkole.service.NewsService;

import pl.kielce.tu.przedszkole.przedszkole.model.New;

import java.util.List;

public interface NewsService {
    void addNews(String issuingUserName, New news) throws Exception;

    void editNews(String issuingUserName, New news) throws Exception;

    void deleteNews(String issuingUserName, Long newsId) throws Exception;

    List<New> getNewsList();

    New getNewsById(Long newsId);
}
