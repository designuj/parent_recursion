package pl.designuj.projects.wiring;

import com.google.common.base.Strings;
import pl.designuj.projects.model.MyObject;
import pl.designuj.projects.model.ObjectPair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * created by designuj on 17/07/2019
 */

public class Autowire {

    public List<ObjectPair> getObjectPairs(List<MyObject> myObjects) {
        List<ObjectPair> objectPairs = new ArrayList<>();

        Map<String, MyObject> objectMap = myObjects.stream()
                .filter(s -> !Strings.isNullOrEmpty(s.getId()))
                .collect(Collectors.toMap(MyObject::getId, Function.identity()));

        objectMap.forEach((id, main) -> {
            MyObject root = getRoot(main, objectMap);
            if (!root.getId().equals(main.getId())) {
                objectPairs.add(ObjectPair.builder().mainObject(main).rootObject(root).build());
            }});

        return objectPairs;
    }

    //recursion
    private MyObject getRoot(MyObject myObject, Map<String, MyObject> objectMap) {
        MyObject result = myObject;

        String resultId = result.getRootId();
        String resultIdByDate = getRootIdFoundByDate(result, objectMap);

        if (!Strings.isNullOrEmpty(resultId) && objectMap.containsKey(resultId)) {
            result = getRoot(objectMap.get(resultId), objectMap);
        } else if (!Strings.isNullOrEmpty(resultIdByDate)) {
            result = getRoot(objectMap.get(resultIdByDate), objectMap);
        }

        return result;
    }

    private String getRootIdFoundByDate(MyObject mainObject, Map<String, MyObject> riskMap) {
        String result = null;

        Optional<Date> mainObjectStart = Optional.ofNullable(mainObject)
                .map(MyObject::getStartDate);

        if (mainObjectStart.isPresent()) {
            for (Map.Entry<String, MyObject> entry : riskMap.entrySet()) {
                Optional<Date> rootObjectEnd = Optional.ofNullable(entry.getValue())
                        .map(MyObject::getEndDate);

                if (rootObjectEnd.isPresent()) {
                    boolean datesOverlaps = mainObjectStart.get().before(rootObjectEnd.get());

                    if (datesOverlaps) {
                        result = entry.getKey();
                    }
                }
            }
        }

        return result;
    }
}
