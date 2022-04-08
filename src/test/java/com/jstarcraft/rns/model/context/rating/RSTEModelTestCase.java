package com.jstarcraft.rns.model.context.rating;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.jstarcraft.ai.evaluate.Evaluator;
import com.jstarcraft.ai.evaluate.rating.MAEEvaluator;
import com.jstarcraft.ai.evaluate.rating.MPEEvaluator;
import com.jstarcraft.ai.evaluate.rating.MSEEvaluator;
import com.jstarcraft.core.common.configuration.Configurator;
import com.jstarcraft.core.common.configuration.string.MapConfigurator;
import com.jstarcraft.rns.task.RatingTask;

import it.unimi.dsi.fastutil.objects.Object2FloatSortedMap;

public class RSTEModelTestCase {

    @Test
    public void testRecommender() throws Exception {
        Properties keyValues = new Properties();
        keyValues.load(this.getClass().getResourceAsStream("/data/filmtrust.properties"));
        keyValues.load(this.getClass().getResourceAsStream("/model/context/rating/rste-test.properties"));
        Configurator configuration = new MapConfigurator(keyValues);
        RatingTask job = new RatingTask(RSTEModel.class, configuration);
        Object2FloatSortedMap<Class<? extends Evaluator>> measures = job.execute();
        Assert.assertEquals(0.64303476F, measures.getFloat(MAEEvaluator.class), 0F);
        Assert.assertEquals(0.99206346F, measures.getFloat(MPEEvaluator.class), 0F);
        Assert.assertEquals(0.6777738F, measures.getFloat(MSEEvaluator.class), 0F);
    }

}
