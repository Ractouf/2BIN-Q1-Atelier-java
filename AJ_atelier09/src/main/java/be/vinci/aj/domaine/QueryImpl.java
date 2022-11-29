package be.vinci.aj.domaine;

class QueryImpl implements Query {
    private String url;
    private QueryMethod method;

    public QueryImpl(String url, QueryMethod method) {
        this.url = url;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public enum QueryMethod { GET, POST };
}
