package central;

import static org.junit.Assert.*;

import central.models.GovDataRubric;
import central.models.GovDataConstantHolderForParse;
import central.services.GovDataRubricService;
import org.junit.Test;

import java.util.List;

/**
 * Created by Bismark on 27.08.2016.
 */

public class GovDataRubricServiceTest {

    @Test
    public void testGetAllRubricsFromUrl() {
        String url = "http://data.gov.ua/datasets";
        assertNotNull(url);
        IGovDataRubricService testService = new GovDataRubricService();
        List<GovDataRubric> rubrics = testService.getAllRubricsFromUrl(url, new GovDataConstantHolderForParse());
        rubrics.forEach((r) -> {
            System.out.println(r.getName() + " " + r.getCountDocs() + " " + r.getUrl());
        });
    }
}
