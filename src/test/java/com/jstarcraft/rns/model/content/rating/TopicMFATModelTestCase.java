package com.jstarcraft.rns.model.content.rating;

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

public class TopicMFATModelTestCase {

    @Test
    public void testRecommender() throws Exception {
        Properties keyValues = new Properties();
        keyValues.load(this.getClass().getResourceAsStream("/data/musical_instruments.properties"));
        keyValues.load(this.getClass().getResourceAsStream("/model/content/topicmfat-test.properties"));
        Configurator configuration = new Configurator(keyValues);
        RatingTask job = new RatingTask(TopicMFATModel.class, configuration);
        Object2FloatSortedMap<Class<? extends Evaluator>> measures = job.execute();
        Assert.assertEquals(0.61896443F, measures.getFloat(MAEEvaluator.class), 0F);
        Assert.assertEquals(0.9873356F, measures.getFloat(MPEEvaluator.class), 0F);
        Assert.assertEquals(0.7254535F, measures.getFloat(MSEEvaluator.class), 0F);
    }

}
