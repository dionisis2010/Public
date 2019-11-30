package ru.deda.team.innorumors.model.content.dto;

import ru.deda.team.innorumors.model.content.type.Post;

import java.util.Set;

public class TagDTO {
    Long id;
    String name;
    Set<Post> postS;
}
