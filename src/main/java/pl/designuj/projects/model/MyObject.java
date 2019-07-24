package pl.designuj.projects.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * created by designuj on 17/07/2019
 */

@Builder
@Data
public class MyObject {
    private String id;
    private String rootId;
    private Date startDate;
    private Date endDate;
}
