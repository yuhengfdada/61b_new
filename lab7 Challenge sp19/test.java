import static org.junit.Assert.*;
import org.junit.Test;


public class test {
    @Test
    public void testing(){
        assertEquals(0, ExperimentHelper.optimalIPL(1));
        assertEquals(1, ExperimentHelper.optimalIPL(2));
        assertEquals(2, ExperimentHelper.optimalIPL(3));
        assertEquals(4, ExperimentHelper.optimalIPL(4));
        assertEquals(6, ExperimentHelper.optimalIPL(5));
        assertEquals(0, ExperimentHelper.optimalAverageDepth(1),0.001);
        assertEquals(1.2, ExperimentHelper.optimalAverageDepth(5),0.001);
        assertEquals(1.625, ExperimentHelper.optimalAverageDepth(8),0.001);

    }
}
