package dev.struchkov.godfather.main.domain;


import dev.struchkov.godfather.main.domain.content.Message;

public class StorylineHistory {

    private String personId;
    private String unitName;
    private Message message;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
