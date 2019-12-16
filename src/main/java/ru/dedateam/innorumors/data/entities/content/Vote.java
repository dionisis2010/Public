package ru.dedateam.innorumors.data.entities.content;

import lombok.Getter;

@Getter
public enum Vote {
    Like("like", +1),
    DISLIKE("dislike", - 1);

    private String name;
    private Integer rat;

    Vote(String name, Integer rat) {
        this.name = name;
        this.rat = rat;
    }
}
