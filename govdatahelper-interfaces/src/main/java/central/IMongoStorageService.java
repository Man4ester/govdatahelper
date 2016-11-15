package central;

import central.models.GovDataFinalEntity;
import central.models.GovDataItem;
import central.models.GovDataRubric;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.List;

/**
 * Created by Bismark on 15.10.2016.
 */
public interface IMongoStorageService {

    default MongoDatabase loadDatabase() {
        MongoClient client = new MongoClient();
        return client.getDatabase("govdatahelper");
    }

    /**
     * Simple save operation with transforming to MongoDB object
     * @param r
     * @throws NullPointerException throws if it is not present r
     */
    void saveGovDataRubric(GovDataRubric r) throws NullPointerException;

    /**
     *Simple clean from DB
     */
    void cleanAllGovDataRubric();

    /**
     * Simple save operation with transforming to MongoDB object
     * @param item
     * @throws NullPointerException throws if it is not present item
     */
    void saveGovDataItem(GovDataItem item) throws NullPointerException;

    /**
     * Full search from DB
     * @return lst with results
     */
    List<GovDataFinalEntity> findAllGovDataFinalEntity();

    /**
     * Simple clean from DB
     */
    void cleanAllGovDataItem();

    /**
     * Simple save operation with transforming to MongoDB object
     * @param entity
     * @throws NullPointerException if it is not filled entity
     */
    void saveGovDataFinalEntity(GovDataFinalEntity entity) throws NullPointerException;

    /**
     * Simple clean
     */
    void cleanAllGovDataFinalEntity();

    /**
     * Full search
     * @return
     */
    List<GovDataRubric> getAllRubricsFromDB();

    /**
     * Full search
     * @return
     */
    List<GovDataItem> findAllGovDataItems();

    /**
     * Full search by parameter
     * @param rubricNme
     * @return
     */
    List<GovDataItem> finaGovDataItemByRubricName(String rubricNme);

}
