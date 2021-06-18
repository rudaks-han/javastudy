package rudaks.temp;

public interface JsonSerializable {
    default String toJson() {
        return JsonUtil.toJson(this);
    }
}
