package central.models;

import com.mongodb.Block;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bismark on 15.11.2016.
 */
public class GovDataItemBlock<T> implements Block<T> {

    List<GovDataItem> items = new ArrayList<>();

    public GovDataItemBlock(List<GovDataItem> items) {
        this.items = items;
    }

    @Override
    public void apply(T t) {
        Document document = (Document) t;
        GovDataItem r = new GovDataItem();
        r.setRubric(document.getString("rubric"));
        r.setName(document.getString("name"));
        r.setLink(document.getString("link"));
        items.add(r);
    }
}
