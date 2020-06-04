

public class User {
    private String userId;
    private String name;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        // 구현코드
        return "";
    }

    @Override
    public boolean equals(Object o) {
        // 구현코드
        return true;
    }

    @Override
    public int hashCode() {
        // 구현코드
        return -1;
    }
}
