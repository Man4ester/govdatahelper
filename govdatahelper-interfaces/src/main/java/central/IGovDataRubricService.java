package central;

import central.models.ConstantHolderForParse;
import central.models.GovDataFinalEntity;
import central.models.GovDataItem;
import central.models.GovDataRubric;

import java.util.List;

/**
 * Created by Bismark on 27.08.2016.
 */
public interface IGovDataRubricService {
    /**
     * Need real all rubrics from url
     * @param url - required
     * @param holder - contains constants
     * @return list of rubrics
     * @throws NullPointerException if it is not present url
     */
    List<GovDataRubric> getAllRubricsFromUrl(String url, ConstantHolderForParse holder) throws NullPointerException;

    /**
     *
     * @param holder - required
     * @param rubric -required
     * @param page
     * @return list of models
     * @throws NullPointerException if it is not filled required
     */
    List<GovDataItem> getAllGovDataItemFromRubric(ConstantHolderForParse holder, GovDataRubric rubric, int page) throws NullPointerException;

    /**
     *
     * @param holder
     * @param item
     * @return
     * @throws NullPointerException
     */
    GovDataFinalEntity getInfoAboutEntityByItem(ConstantHolderForParse holder,GovDataItem item) throws NullPointerException;



}
