package central;

import static org.junit.Assert.*;

import central.models.GovDataFinalEntity;
import central.models.GovDataItem;
import central.models.GovDataRubric;
import central.models.GovDataConstantHolderForParse;
import central.services.GovDataRubricService;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
        List<GovDataRubric> rubrics = testService.getAllRubricsFromUrl(url, new GovDataConstantHolderForParse());
        rubrics.forEach((r) -> {
            System.out.println(r.getName() + " " + r.getCountDocs() + " " + r.getUrl());
        });
    }

    @Test
    public void testGetAllGovDataItemFromRubric() {
        String url = "http://data.gov.ua/datasets";
        assertNotNull(url);
        IGovDataRubricService testService = new GovDataRubricService();
        List<GovDataRubric> rubrics = testService.getAllRubricsFromUrl(url, new GovDataConstantHolderForParse());
        if (rubrics.isEmpty()) {
            throw new NullPointerException("No result");
        }
        GovDataRubric rubricForTest = rubrics.get(0);
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
        });
    }

    @Test
    public void getInfoAoutFinalEntity() {
        IGovDataRubricService testService = new GovDataRubricService();
        GovDataItem item = new GovDataItem();
        item.setRubric("Будівництво");
        item.setLink("passport/64c228c5-d414-4c79-bd8b-4409a897abfb");
        GovDataFinalEntity result = testService.getInfoAboutEntityByItem(new GovDataConstantHolderForParse(), item);
        assertNotNull(result);
        LOGGER.info(result.toString());
    }
}
