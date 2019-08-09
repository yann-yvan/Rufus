package corp.ny.com.rufus.database;

import java.util.List;

/**
 * Created by Yann Yvan CEO of N.Y. Corp. on 07/05/18.
 */
public class Query<Y> {
    public Query<Y> where(String fValue, String sValue) {
        return this;
    }

    public List<Model<Y>> get() {
        return null;
    }

    public boolean delete() {
        return false;
    }

    public Model<Y> update() {
        return null;
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

        private String value;

        Comparison(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
