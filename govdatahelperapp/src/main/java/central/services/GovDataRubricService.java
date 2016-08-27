package central.services;

import central.IGovDataRubricService;
import central.models.ConstantHolderForParse;
import central.models.GovDataRubric;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
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
}
