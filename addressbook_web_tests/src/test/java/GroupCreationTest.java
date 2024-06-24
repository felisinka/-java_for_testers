import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupCreationTest extends TestBase {

    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup("group name", "group header", "group footer");
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup("", "", "");

    }

}
