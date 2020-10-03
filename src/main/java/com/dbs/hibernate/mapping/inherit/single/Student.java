package com.dbs.hibernate.mapping.inherit.single;

public class Student extends Person {

    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "Student [cardId=" + cardId + ", getId()=" + getId()
                + ", getName()=" + getName() + "]";
    }
}
