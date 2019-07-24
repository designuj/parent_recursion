package pl.designuj.projects.model;

import lombok.Builder;
import lombok.Data;

/**
 * created by designuj on 17/07/2019
 */

@Builder
@Data
public class ObjectPair {
    private MyObject mainObject;
    private MyObject rootObject;
}
