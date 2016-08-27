package central.models;

import central.models.ConstantHolderForParse;

/**
 * Created by Bismark on 27.08.2016.
 */
public class GovDataConstantHolderForParse extends ConstantHolderForParse {
    @Override
    public String getRootTag() {
        return "view-category-dataset-views";
    }

    @Override
    public String getContentWithItems() {
        return "views-row";
    }
}

