package central.models;

import java.util.List;

/**
 * Created by Bismark on 25.09.2016.
 */
public class GovDataFinalEntity {

    public static final String COLLECTIONS_NAME="gov_data_final_entity";

    private String id;

    private String version;

    private String language;

    private String formats;

    private String informationsOwner;

    private String personInfo;

    private String email;

    public String getRubric() {
        return rubric;
    }

    public void setRubric(String rubric) {
        this.rubric = rubric;
    }

    private String rubric;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }

    public String getInformationsOwner() {
        return informationsOwner;
    }

    public void setInformationsOwner(String informationsOwner) {
        this.informationsOwner = informationsOwner;
    }

    public String getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "GovDataFinalEntity{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", language='" + language + '\'' +
                ", formats=" + formats +
                ", informationsOwner='" + informationsOwner + '\'' +
                ", personInfo='" + personInfo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
