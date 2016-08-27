package central;

import central.models.ConstantHolderForParse;
import central.models.GovDataRubric;

import java.util.List;

/**
 * Created by Bismark on 27.08.2016.
 */
public interface IGovDataRubricService {
    /**
     * Need real all rubrics from url
     * @param url - required
     * @return list of rubrics
     * @throws NullPointerException if it is not present url
     */
    List<GovDataRubric> getAllRubricsFromUrl(String url, ConstantHolderForParse holder) throws NullPointerException;

}
