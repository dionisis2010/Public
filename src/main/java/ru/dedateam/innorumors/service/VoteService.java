package ru.dedateam.innorumors.service;

import org.springframework.stereotype.Service;
import ru.dedateam.innorumors.data.repositories.VotePostRepo;

@Service
public class VoteService {

    private VotePostRepo votePostRepo;
    public Integer calculateRatingPost(Long postId){
        return 0;
    }
}
