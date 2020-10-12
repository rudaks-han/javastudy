package rudaks;

public interface JsonSerializable {
    default String toJson() {
        return JsonUtil.toJson(this);
    }
}
