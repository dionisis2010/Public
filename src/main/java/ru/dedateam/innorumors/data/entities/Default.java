package ru.dedateam.innorumors.data.entities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.User;

import java.text.SimpleDateFormat;

@Configuration
public class Default {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd mm yyyy");

//    public static final User MISHA = new User(1L, "Misha", "123");
//
//    public static  final Post POST_1 = new Post(1L, MISHA, false, "Три дня в армии",
//            "Привезли к нам во вторник два камаза щебня, чтобы насыпать дорожку от шоссе к КПП. Высыпали всё это добро перед КПП, дали команду прокладывать путь. Дорогу насыпали, на неё хватило щебня с одного камаза, оставшаяся куча осталась лежать перед КПП.\n" +
//                    "В среду командир батальона приказал оставшийся щебень раскидать по прилегающей территории. К слову, за ночь щебень замерз, поэтому сперва пришлось долго и упорно долбить его киркой. К концу дня управились, довольные ушли в казарму.\n" +
//                    "В четверг командиру батальона позвонил начальник штаба полка, сказал, что разбросанный по территории щебень необходимо скидать в кучу, ни к чему портить газон. Весь день собирали раскиданный щебень в кучу.\n" +
//                    "Сегодня на работу вышел командир полка, спросил, какого хрена перед КПП лежит гигантская куча щебня, и приказал раскидать его по территории. Раскидали, ждем завтрашнего дня.");
//
//    public static Comment comment = new Comment(MISHA, "Comment Comment Comment Comment Comment Comment ", POST_1);
}
