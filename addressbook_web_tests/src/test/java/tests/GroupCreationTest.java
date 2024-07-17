package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
   /*     for (var name: List.of("","group name")){
            for (var header: List.of("","header")){
                for (var footer: List.of("","footer")){
                    result.add(new GroupData()
                            .withName(name)
                            .withHeader(header)
                            .withFooter(footer));
                }
            }
        }*/
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.xml"),  new TypeReference<List<GroupData>>() { } );
        result.addAll(value);
        return result;
    }

    public static List<GroupData> singleRandomGroup() throws IOException {
        return List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10)));
    }


        @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();

        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size()-1).id();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

       /* var newUIGroups = app.groups().getList();
        newUIGroups.sort(compareById);
        var maxUIId = newUIGroups.get(newUIGroups.size()-1).id();
        var expectedUIList = new ArrayList<>(oldGroups);
        expectedUIList.add(group.withId(maxUIId).withHeader("").withFooter(""));
        expectedUIList.sort(compareById);
        Assertions.assertEquals(newUIGroups, expectedUIList);*/
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(new GroupData().withName("group name'")));
        return result;
    }


    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void cannotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(oldGroups,newGroups);
    }

 /*   @ParameterizedTest
    @ValueSource(strings = {"group name", "group name'"})
    public void canCreateGroup(String name) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData(name, "group header", "group footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount+1,newGroupCount);
    }*/

}
