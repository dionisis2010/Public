package ru.dedateam.innorumors.data.repositories;

import javafx.geometry.Pos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Post;

import java.util.List;

@Repository
public interface PostRepo extends PagingAndSortingRepository<Post, Long> {
}