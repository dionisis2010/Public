package ru.dedateam.innorumors.data.entities.content;

import lombok.Getter;

@Getter
public enum Vote {
    LIKE("like", +1),
    DISLIKE("dislike", -1);

    private String name;
    private Integer rat;

    Vote(String name, Integer rat) {
        this.name = name;
        this.rat = rat;
    }

    public static Vote parse(String name) {
        if (name.equals("like")){
            return Vote.LIKE;
        } else if ((name.equals("dislike"))){
            return Vote.DISLIKE;
        } else {
            throw new RuntimeException();
        }
    }
}
