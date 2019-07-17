package pl.designuj.projects.wiring;

import com.google.common.collect.Lists;
import org.junit.Test;
import pl.designuj.projects.model.MyObject;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AutowireTest {

    @Test(expected = NullPointerException.class)
    public void testMe() {
        List<MyObject> myObjects = Lists.newArrayList(
                MyObject.builder().id("1").rootId(null).build(),
                MyObject.builder().id("2").rootId("1").build(),
                MyObject.builder().id("3").rootId("2").build(),
                MyObject.builder().id("4").rootId("3").build());

        assertTrue(Autowire.getObjectsPairs(myObjects).size() == 3);
    }

}
