package com.jstarcraft.rns.model.benchmark.rating;

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

public class ConstantGuessModelTestCase {

    @Test
    public void testRecommender() throws Exception {
        Properties keyValues = new Properties();
        keyValues.load(this.getClass().getResourceAsStream("/data/filmtrust.properties"));
        keyValues.load(this.getClass().getResourceAsStream("/model/benchmark/constantguess-test.properties"));
        Configurator configuration = new MapConfigurator(keyValues);
        RatingTask job = new RatingTask(ConstantGuessModel.class, configuration);
        Object2FloatSortedMap<Class<? extends Evaluator>> measures = job.execute();
        Assert.assertEquals(1.0560759F, measures.getFloat(MAEEvaluator.class), 0F);
        Assert.assertEquals(1.0F, measures.getFloat(MPEEvaluator.class), 0F);
        Assert.assertEquals(1.4230907F, measures.getFloat(MSEEvaluator.class), 0F);

    }

}
