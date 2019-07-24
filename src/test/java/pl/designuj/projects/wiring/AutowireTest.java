package pl.designuj.projects.wiring;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import pl.designuj.projects.model.MyObject;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AutowireTest {

    private Autowire autowire;

    @Before
    public void init() {
        autowire = new Autowire();
    }

    @Test
    public void tesParentsId_shouldMatchAll() {
        List<MyObject> myObjects = Lists.newArrayList(
                MyObject.builder().id("1").rootId(null).build(),
                MyObject.builder().id("2").rootId("1").build(),
                MyObject.builder().id("3").rootId("2").build(),
                MyObject.builder().id("4").rootId("3").build());

        assertTrue(autowire.getObjectPairs(myObjects).size() == 3);
    }

    @Test
    public void tesParentsId_shouldMatchSome() {
        List<MyObject> myObjects = Lists.newArrayList(
                MyObject.builder().id("1").rootId(null).build(),
                MyObject.builder().id("2").rootId(null).build(),
                MyObject.builder().id("3").rootId("2").build(),
                MyObject.builder().id("4").rootId(null).build());

        assertTrue(autowire.getObjectPairs(myObjects).size() == 1);
    }

    @Test
    public void tesParentsId_shouldNotMatch() {
        List<MyObject> myObjects = Lists.newArrayList(
                MyObject.builder().id("1").rootId(null).build(),
                MyObject.builder().id("2").rootId(null).build(),
                MyObject.builder().id("3").rootId(null).build(),
                MyObject.builder().id("4").rootId(null).build());

        assertTrue(autowire.getObjectPairs(myObjects).isEmpty());
    }

    @Test
    public void testMixed_shouldMatchAll() {
        List<MyObject> myObjects = Lists.newArrayList(
                MyObject.builder().id("1").endDate(Date.valueOf("2011-10-14")).build(),
                MyObject.builder().id("2").startDate(Date.valueOf("2010-04-05")).build(),
                MyObject.builder().id("3").rootId("2").endDate(Date.valueOf("1990-04-25")).build(),
                MyObject.builder().id("4").rootId(null).startDate(Date.valueOf("1990-01-01")).build());

        assertTrue(autowire.getObjectPairs(myObjects).size() == 3);
    }
}
