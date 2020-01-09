package udemy.simplefactory;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Post {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime publishedOn;

}
