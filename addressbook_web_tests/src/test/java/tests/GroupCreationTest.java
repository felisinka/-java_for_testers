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
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static Stream<GroupData> randomGroups() throws IOException {
        Supplier<GroupData> randomGroup = ()->new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30));
        return Stream.generate(randomGroup).limit(3);

    }

    @ParameterizedTest
    @MethodSource("randomGroups")
    public void canCreateGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        var extraGroups = newGroups.stream().filter(g->!oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));

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
        var oldGroups = app.hbm().getGroupList();//groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();//groups().getList();
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
