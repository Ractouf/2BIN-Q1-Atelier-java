package be.vinci.aj.domaine;

public class QueryFactory {
    public static QueryImpl getQuery() {
        return new QueryImpl(null, null);
    }
}
