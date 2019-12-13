package ru.dedateam.innorumors.data;

public enum  TestViews {

    ALL_USERS("test/all_users");

    private String nameView;


    TestViews(String nameView) {
        this.nameView = nameView;
    }

    @Override
    public String toString() {
        return nameView;
    }
}
