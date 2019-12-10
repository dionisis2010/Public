package ru.dedateam.innorumors.data.entities.content;

import lombok.Getter;
import lombok.Setter;
import ru.dedateam.innorumors.data.entities.Default;
import ru.dedateam.innorumors.data.entities.profiles.User;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Post {

    private Long id;

    private User author;
    private boolean isAnonymous;

    private String title;
    private String body;

    private Date postedTime;

    private List<Comment> comments;



    public Post() {
    }

    public Boolean addComment(Comment comment) {
        this.getComments().add(comment);
        return true;
    }

    public String getFormatPostedTime() {
        return Default.DATE_FORMAT.format(this.postedTime);
    }


//    @Override
//    public String toString() {
//        return "Author: " + getAuthor().getNickName() + " " + isAnonymous()
//                + "\nTitle" + getTitle()
//                + "\n" + getBody()
//                + "\n" + getFormatPostedTime()
//                + "\nComments: " + getComments().size();
//    }
}
