package com.jstarcraft.rns.model.collaborative.rating;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.jstarcraft.ai.evaluate.Evaluator;
import com.jstarcraft.ai.evaluate.rating.MAEEvaluator;
import com.jstarcraft.ai.evaluate.rating.MPEEvaluator;
import com.jstarcraft.ai.evaluate.rating.MSEEvaluator;
import com.jstarcraft.core.common.configuration.Configurator;
import com.jstarcraft.rns.task.RatingTask;

import it.unimi.dsi.fastutil.objects.Object2FloatSortedMap;

public class FMALSModelTestCase {

    @Test
    public void testRecommender() throws Exception {
        Properties keyValues = new Properties();
        keyValues.load(this.getClass().getResourceAsStream("/data/filmtrust.properties"));
        keyValues.load(this.getClass().getResourceAsStream("/model/collaborative/rating/fmals-test.properties"));
        Configurator configuration = new Configurator(keyValues);
        RatingTask job = new RatingTask(FMALSModel.class, configuration);
        Object2FloatSortedMap<Class<? extends Evaluator>> measures = job.execute();
        Assert.assertEquals(0.6478765F, measures.getFloat(MAEEvaluator.class), 0F);
        Assert.assertEquals(0.96031743F, measures.getFloat(MPEEvaluator.class), 0F);
        Assert.assertEquals(0.736361F, measures.getFloat(MSEEvaluator.class), 0F);
    }

}
