package blog.ifelse.type4_enum;

public enum Operator {
    ADD {
        @Override
        public int apply(int a, int b) {
            return a + b;
        }
    };

    public abstract int apply(int a, int b);
}
