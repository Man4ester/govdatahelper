package central.models;

/**
 * Created by Bismark on 27.08.2016.
 */
public class GovDataRubric {

    public static final String COLLECTIONS_NAME="gov_data_rubric";

    private String name;

    private String url;

    private int countDocs;

    public GovDataRubric() {
    }

    public GovDataRubric(String name, String url, int countDocs) {
        this.name = name;
        this.url = url;
        this.countDocs = countDocs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCountDocs() {
        return countDocs;
    }

    public void setCountDocs(int countDocs) {
        this.countDocs = countDocs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GovDataRubric{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", countDocs=" + countDocs +
                '}';
    }
}
