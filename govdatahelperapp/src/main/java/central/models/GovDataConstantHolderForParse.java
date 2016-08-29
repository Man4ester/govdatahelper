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

    @Override
    public String getUrlForItem() {
        return "http://data.gov.ua/datasets/" +
                "%s" +
                "?field_organization_value=" +
                "&title=" +
                "&sort_bef_combine=created%20DESC" +
                "&sort_order=DESC" +
                "&sort_by=created" +
                "&page=%d";
    }
}

