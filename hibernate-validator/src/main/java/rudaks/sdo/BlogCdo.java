package rudaks.sdo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rudaks.share.JsonUtil;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class BlogCdo {

    private String id;

    @NotEmpty
    private String name;

    @Size(min = 1, max = 10)
    private String title;

    @Min(1)
    @Max(2)
    private long version;

    public String toString() {
        return JsonUtil.toJson(this);
    }

    public static BlogCdo sample() {
        return new BlogCdo("rudaks", "루닥스", "제목", 1L);
    }

    public static void main(String[] args) {
        System.out.println(sample());
    }

}
