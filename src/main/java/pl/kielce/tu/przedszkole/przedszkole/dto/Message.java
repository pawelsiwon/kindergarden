package pl.kielce.tu.przedszkole.przedszkole.dto;

import lombok.Data;

@Data
public class Message {
    Integer status;
    String message;

    public Message() {

    }
    public Message(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}
