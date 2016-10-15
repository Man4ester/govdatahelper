package central.models;

/**
 * Created by Bismark on 28.08.2016.
 */
public class GovDataItem {

    public static final String COLLECTIONS_NAME="gov_data_item";

    private String rubric;

    private String name;

    private String link;

    public String getRubric() {
        return rubric;
    }

    public void setRubric(String rubric) {
        this.rubric = rubric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "GovDataItem{" +
                "rubric=" + rubric +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
