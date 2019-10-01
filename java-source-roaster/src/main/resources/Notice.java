import com.spectra.pilot.share.domain.NameValue;
import com.spectra.pilot.share.domain.NameValueList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends NoticeKey {

    private String title;
	private String content;
	private boolean importance;
	private long fromDate;
	private String createdBy;
	private long createdAt;

    public Notice(NoticeKey noticeKey, String title, String content, boolean importance, long fromDate, String createdBy) {
        super(noticeKey);
        this.title = title;
		this.content = content;
		this.importance = importance;
		this.fromDate = fromDate;
		this.createdBy = createdBy;
    }

    public Notice(NoticeKey noticeKey, String title, String content, boolean importance, long fromDate, String createdBy, long createdAt) {
        super(noticeKey);
        this.title = title;
		this.content = content;
		this.importance = importance;
		this.fromDate = fromDate;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
    }

    public void setValues(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            switch (nameValue.getName()) {
                case "title":
					this.title = nameValue.getValue();
					break;
				case "content":
					this.content = nameValue.getValue();
					break;
				case "importance":
					this.importance = Boolean.parseBoolean(nameValue.getValue());
					break;
				case "fromDate":
					this.fromDate = Long.parseLong(nameValue.getValue());
					break;
                default:
                    throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }

    public String toString() {
        return toJson();
    }

    public static Notice sample() {
        return new Notice(new NoticeKey("attic"), "sample", "sample", true, 1234, "sample");
    }

    public static void main(String[] args) {
        System.out.println(sample());
    }
}
