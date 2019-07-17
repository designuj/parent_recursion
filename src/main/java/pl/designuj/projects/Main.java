package pl.designuj.projects;

import com.google.common.collect.Lists;
import pl.designuj.projects.model.MyObject;
import pl.designuj.projects.wiring.Autowire;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<MyObject> myObjects = Lists.newArrayList(
                MyObject.builder().id("1").rootId(null).build(),
                MyObject.builder().id("2").rootId("1").build(),
                MyObject.builder().id("3").rootId("2").build(),
                MyObject.builder().id("4").rootId("3").build());

        Autowire.getObjectsPairs(myObjects).forEach(System.out::println);
    }
}
