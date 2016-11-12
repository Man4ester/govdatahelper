package central.services;

import central.IGovDataRubricService;
import central.models.ConstantHolderForParse;
import central.models.GovDataFinalEntity;
import central.models.GovDataItem;
import central.models.GovDataRubric;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Bismark on 27.08.2016.
 */
public class GovDataRubricService implements IGovDataRubricService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GovDataRubricService.class);

    @Override
    public List<GovDataRubric> getAllRubricsFromUrl(String url, ConstantHolderForParse holder) throws NullPointerException {
        LOGGER.info(String.format("getAllRubricsFromUrl from %s", url));
        if (!Optional.ofNullable(url).isPresent()) {
            throw new NullPointerException("url can't be NULL");
        }
        List<GovDataRubric> rubrics = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).timeout(holder.getTimeout()).get();
            Elements menuBar = doc.getElementsByClass(holder.getRootTag());
            menuBar.stream().forEach((s) -> {
                Elements l = s.getElementsByClass(holder.getContentWithItems());
                l.stream().forEach((ls) -> {
                    GovDataRubric rubric = new GovDataRubric(
                            ls.getElementsByTag("a").get(0).text(),
                            ls.getElementsByTag("a").get(0).attr("href"),
                            Integer.parseInt(ls.getElementsByTag("span").get(2).text()));
                    rubrics.add(rubric);

                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rubrics;
    }


    @Override
    public List<GovDataItem> getAllGovDataItemFromRubric(ConstantHolderForParse holder, GovDataRubric rubric, int page) throws NullPointerException {
        LOGGER.info("getAllGovDataItemFromRubric");
        if (!Optional.ofNullable(holder).isPresent()) {
            throw new NullPointerException("holder is NULL");
        }
        if (!Optional.ofNullable(rubric).isPresent()) {
            throw new NullPointerException("rubric is null");
        }
        List<GovDataItem> govDataItems = new ArrayList<>();
        String url = String.format(holder.getUrlForItem(), rubric.getName(), page);
        try {
            Document doc = Jsoup.connect(url).timeout(holder.getTimeout()).get();
            Elements content = doc.getElementsByClass("view-content");
            Elements mainContent = content.get(1).getElementsByClass("views-row");
            mainContent.forEach((c) -> {
                Elements details = c.getElementsByClass("views-field-field-big-title");
                Element sigle = details.get(0);

                GovDataItem govDataItem = new GovDataItem();
                govDataItem.setLink(sigle.getElementsByTag("a").attr("href"));
                govDataItem.setName(sigle.getElementsByTag("a").text());
                govDataItem.setRubric(rubric.getName());
                govDataItems.add(govDataItem);
            });
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error(e.getMessage());
            return govDataItems;
        }
        return govDataItems;
    }

    @Override
    public GovDataFinalEntity getInfoAboutEntityByItem(ConstantHolderForParse holder, GovDataItem item) throws NullPointerException {
        LOGGER.info("getInfoAboutEntityByItem");
        GovDataFinalEntity result = null;
        if (!Optional.ofNullable(holder).isPresent()) {
            throw new NullPointerException("Not present holder");
        }
        if (!Optional.ofNullable(item).isPresent()) {
            throw new NullPointerException("Not present item");
        }
        if (!Optional.ofNullable(item.getLink()).isPresent()) {
            throw new NullPointerException("No url from item to search");
        }
        String url = holder.getUtlForFinalEntity(item.getLink());
        try {
            result = new GovDataFinalEntity();
            Document doc = Jsoup.connect(url).timeout(holder.getTimeout()).get();
            Elements root = doc.getElementsByClass("field-group-format-wrapper");
            Element source = root.get(0);
            String datasetId = getValueFromElememt(source, "field-name-datasetid");
            String fieldNameIdRevision = getValueFromElememt(source, "field-name-id-revision");
            String fieldLanguage = getValueFromElememt(source, "field-name-field-language");
            String fieldAuthor = getValueFromElememt(source, "field-name-author");
            String fieldAllName = getValueFromElememt(source, "field-name-field-all-name");
            String fieldEmail = getValueFromElememt(source, "field-name-field-email");
            String fieldFormat = getValueFromElememt(source, "field-name-field-format-data-set");

            result.setRubric(item.getRubric());
            result.setEmail(fieldEmail);
            result.setFormats(fieldFormat);
            result.setId(datasetId);
            result.setLanguage(fieldLanguage);
            result.setInformationsOwner(fieldAuthor);
            result.setVersion(fieldNameIdRevision);
            result.setPersonInfo(fieldAllName);
            LOGGER.debug("ANALYZE");
        }catch (HttpStatusException e){
            LOGGER.error(e.getMessage());
        }catch (SocketTimeoutException e){
            LOGGER.error(e.getMessage());
        }
        catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    private String getValueFromElememt(Element source, String rootId) {
        LOGGER.info("getValueFromElememt");
        StringBuilder val = new StringBuilder();
        if(source.getElementsByClass(rootId).isEmpty()){
            return "";
        }
        Element rElement = source.getElementsByClass(rootId).get(0);
        Elements subElemenst = rElement.getElementsByClass("field-item");
        subElemenst.forEach(c -> {
            LOGGER.debug("INSIDE SUBELEMENTS");
            String tmpVal = c.getElementsByTag("p").text();
            if (tmpVal == null || tmpVal.isEmpty()) {
                tmpVal = c.html();
            }
            val.append(tmpVal).append(", ");
        });
        if (val.length() > 2) {
            val.setLength(val.length() - 2);
        }
        LOGGER.debug("RES: "+val.toString());
        return val.toString();
    }
}
