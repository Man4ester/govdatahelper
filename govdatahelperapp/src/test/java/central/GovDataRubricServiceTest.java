package central;

import central.models.GovDataConstantHolderForParse;
import central.models.GovDataFinalEntity;
import central.models.GovDataItem;
import central.models.GovDataRubric;
import central.services.GovDataRubricService;
import central.services.MongoStorageService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Bismark on 27.08.2016.
 */

public class GovDataRubricServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GovDataRubricServiceTest.class);


    static String url = "http://data.gov.ua/datasets";

    @Test
    public void testGetAllRubricsFromUrl() {
        assertNotNull(url);
        IGovDataRubricService testService = new GovDataRubricService();
        IMongoStorageService mongoService = new MongoStorageService();
        mongoService.cleanAllGovDataRubric();

        List<GovDataRubric> rubrics = testService.getAllRubricsFromUrl(url, new GovDataConstantHolderForParse());
        rubrics.forEach((r) -> {
            System.out.println(r.getName() + " " + r.getCountDocs() + " " + r.getUrl());
            mongoService.saveGovDataRubric(r);
        });
    }

    @Test
    public void testGetAllGovDataItemFromRubric() {
        String url = "http://data.gov.ua/datasets";
        assertNotNull(url);
        IGovDataRubricService testService = new GovDataRubricService();
        if (true) {
            return;
        }
        List<GovDataRubric> rubrics = testService.getAllRubricsFromUrl(url, new GovDataConstantHolderForParse());
        if (rubrics.isEmpty()) {
            throw new NullPointerException("No result");
        }

        IMongoStorageService mongoService = new MongoStorageService();
        for (GovDataRubric rParse : rubrics) {
            GovDataRubric rubricForTest = rParse;
            List<GovDataItem> items = new ArrayList<>();
            int addedCount = 10;
            int currentPage = 0;
            while (addedCount != 0) {
                LOGGER.info("PAGE: " + currentPage);
                List<GovDataItem> tmp = testService.getAllGovDataItemFromRubric(new GovDataConstantHolderForParse(), rubricForTest, currentPage);
                currentPage++;
                addedCount = tmp.size();
                items.addAll(tmp);
            }
            LOGGER.info("TOTAL: " + items.size());
            items.forEach((r) -> {
                LOGGER.info(r.getName() + " " + r.getLink());
                mongoService.saveGovDataItem(r);
            });
        }

    }

    @Test
    public void getInfoAoutFinalEntity() {
        String url = "http://data.gov.ua/datasets";
        assertNotNull(url);

        IGovDataRubricService testService = new GovDataRubricService();
        IMongoStorageService mongoService = new MongoStorageService();
        List<GovDataRubric> rubrics = mongoService.getAllRubricsFromDB();

        for (GovDataRubric rParse : rubrics) {

            List<GovDataItem> tmp = mongoService.finaGovDataItemByRubricName(rParse.getName());
            LOGGER.info("SIZE: " + tmp.size());
            for (GovDataItem itSave : tmp) {
                GovDataFinalEntity result = testService.getInfoAboutEntityByItem(new GovDataConstantHolderForParse(), itSave);
                mongoService.saveGovDataFinalEntity(result);
            }
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.info("DONE");
    }

}