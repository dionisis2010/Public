package ru.deda.team.innorumors.model.profile.type;

public enum Gender {
    MALE{
        @Override
        public String toString() {
            return "male";
        }
    },
    FEMALE{
        @Override
        public String toString() {
            return "female";
        }
    }
}
