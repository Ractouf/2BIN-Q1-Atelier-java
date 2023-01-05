package domaine;

public class QueryFactory {

    public Query getQuery() {
        return new QueryImpl();
    }
}
