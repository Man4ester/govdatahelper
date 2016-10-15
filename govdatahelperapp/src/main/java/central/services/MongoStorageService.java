package central.services;

import central.IMongoStorageService;
import central.models.GovDataItem;
import central.models.GovDataRubric;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Bismark on 15.10.2016.
 */
public class MongoStorageService implements IMongoStorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoStorageService.class);

    private MongoDatabase mongoDatabase;

    @Override
    public void saveGovDataRubric(GovDataRubric r) throws NullPointerException {
        LOGGER.info("saveGovDataRubric");
        if(r==null){
            throw new NullPointerException("r can't be NULL");
        }
        Document docRubric = new Document();
        docRubric.append("url", r.getUrl());
        docRubric.append("countDocs", r.getCountDocs());
        docRubric.append("name", r.getName());
        getDataBase().getCollection(GovDataRubric.COLLECTIONS_NAME).insertOne(docRubric);
        LOGGER.info("saved");
    }

    @Override
    public void cleanAllGovDataRubric() {
        LOGGER.info("cleanAllGovDataRubric");
        getDataBase().getCollection(GovDataRubric.COLLECTIONS_NAME).drop();
    }

    @Override
    public void saveGovDataItem(GovDataItem item) throws NullPointerException {
        LOGGER.info("saveGovDataItem");
        Document dItem = new Document();
        dItem.append("link",item.getLink());
        dItem.append("name",item.getName());
        dItem.append("rubric",item.getRubric());
        getDataBase().getCollection(GovDataItem.COLLECTIONS_NAME).insertOne(dItem);
        LOGGER.info("saved");
    }

    @Override
    public void cleanAllGovDataItem() {
        LOGGER.info("cleanAllGovDataItem");
        getDataBase().getCollection(GovDataItem.COLLECTIONS_NAME).drop();
    }

    MongoDatabase getDataBase() {
        if (mongoDatabase == null) {
            mongoDatabase = loadDatabase();
        }
        return mongoDatabase;
    }

}
