package central;

import central.models.GovDataFinalEntity;
import central.models.GovDataItem;
import central.models.GovDataRubric;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Bismark on 15.10.2016.
 */
public interface IMongoStorageService {

    default MongoDatabase loadDatabase(){
        MongoClient client = new MongoClient();
        return  client.getDatabase("govdatahelper");
    }

    void saveGovDataRubric(GovDataRubric r) throws NullPointerException;

    void cleanAllGovDataRubric();

    void saveGovDataItem(GovDataItem item) throws NullPointerException;

    void cleanAllGovDataItem();

    void saveGovDataFinalEntity(GovDataFinalEntity entity) throws NullPointerException;

    void cleanAllGovDataFinalEntity();




}
