package ru.deda.team.innorumors.model.content.dto;

import ru.deda.team.innorumors.model.content.type.Post;
import ru.deda.team.innorumors.model.profile.type.User;

public class LikeDTO {
    Long id;
    User author;
    Post post;
}
