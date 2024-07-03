package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{

    @Test
    void vanModifyGroup() {
        if (app.groups().getCount()==0) {
            app.groups().createGroup(new GroupData("kate group", "kate group header", "kate group footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
