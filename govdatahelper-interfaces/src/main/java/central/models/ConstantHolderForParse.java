package central.models;

/**
 * Created by Bismark on 27.08.2016.
 */
public abstract class ConstantHolderForParse {

    public String getRootTag() {
        return "";
    }

    public String getContentWithItems(){
        return "";
    }

    public int getTimeout(){
        return 5000;
    }

    public String getUrlForItem(){
        return "";
    }

}
