package com.jstarcraft.rns.model.content.ranking;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.jstarcraft.ai.evaluate.Evaluator;
import com.jstarcraft.ai.evaluate.ranking.AUCEvaluator;
import com.jstarcraft.ai.evaluate.ranking.MAPEvaluator;
import com.jstarcraft.ai.evaluate.ranking.MRREvaluator;
import com.jstarcraft.ai.evaluate.ranking.NDCGEvaluator;
import com.jstarcraft.ai.evaluate.ranking.NoveltyEvaluator;
import com.jstarcraft.ai.evaluate.ranking.PrecisionEvaluator;
import com.jstarcraft.ai.evaluate.ranking.RecallEvaluator;
import com.jstarcraft.core.common.configuration.MapOption;
import com.jstarcraft.core.common.configuration.Option;
import com.jstarcraft.rns.task.RankingTask;

import it.unimi.dsi.fastutil.objects.Object2FloatSortedMap;

public class TFIDFModelTestCase {

    @Test
    public void testRecommender() throws Exception {
        Properties keyValues = new Properties();
        keyValues.load(this.getClass().getResourceAsStream("/data/product.properties"));
        keyValues.load(this.getClass().getResourceAsStream("/model/content/tfidf-test.properties"));
        Option configuration = new MapOption(keyValues);
        RankingTask job = new RankingTask(TFIDFModel.class, configuration);
        Object2FloatSortedMap<Class<? extends Evaluator>> measures = job.execute();
        Assert.assertEquals(0.526974F, measures.getFloat(AUCEvaluator.class), 0F);
        Assert.assertEquals(0.0025012426F, measures.getFloat(MAPEvaluator.class), 0F);
        Assert.assertEquals(0.009865027F, measures.getFloat(MRREvaluator.class), 0F);
        Assert.assertEquals(0.0074107912F, measures.getFloat(NDCGEvaluator.class), 0F);
        Assert.assertEquals(66.74913F, measures.getFloat(NoveltyEvaluator.class), 0F);
        Assert.assertEquals(0.006129954F, measures.getFloat(PrecisionEvaluator.class), 0F);
        Assert.assertEquals(0.012177796F, measures.getFloat(RecallEvaluator.class), 0F);
    }

}
