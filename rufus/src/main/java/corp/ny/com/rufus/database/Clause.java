package corp.ny.com.rufus.database;

public class Clause {
    private String field;
    private String comparator = Comparison.eq.value;
    private String value;

    public Clause(String field, String comparator, String value) {
        this.field = field;
        this.comparator = comparator;
        this.value = value;
    }

    public Clause(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getComparator() {
        return comparator;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s",field,comparator,value);
    }

    public enum Comparison {
        eq("="),
        lt("<"),
        lte("<="),
        gt(">"),
        gte(">="),
        diff("!="),
        in("IN"),
        not_in("NOT IN"),
        between("BETWEEN"),
        is("IS"),
        is_not("IS NOT");

        public String value;

        Comparison(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
