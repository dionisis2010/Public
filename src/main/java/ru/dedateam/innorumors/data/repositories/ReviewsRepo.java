package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.review.Review;

@Repository
public interface ReviewsRepo extends CrudRepository<Review, Long> {
    Integer countAllBy();

    Iterable<Review> findAllByIsDeletedOrderByPostedTime(Boolean isDelete);
}
