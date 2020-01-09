package udemy.simplefactory;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewsPost extends Post {
    private String headline;
    private LocalDate newsTime;
}
