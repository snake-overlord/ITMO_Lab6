package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Basic organization types.
 */
public enum OrganizationType implements Serializable {
    COMMERCIAL,
    PUBLIC,
    OPEN_JOINT_STOCK_COMPANY;

    public static List<String> names() {
        List<String> nameList = new ArrayList<>();
        for (var orgType : values()) {
            nameList.add(orgType.name());
        }
        return nameList;
    }
}
