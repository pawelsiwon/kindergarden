package pl.kielce.tu.przedszkole.przedszkole.dto;

import lombok.Data;
import pl.kielce.tu.przedszkole.przedszkole.model.New;

@Data
public class NewsActionDto {
    LoginData loginData;

    New news;

    Long newsId;
}
