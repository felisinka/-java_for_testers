package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{

    @Test
    void vanModifyGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("kate group", "kate group header", "kate group footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
