package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase{

    public static List<String> userNameProvider() {
        var result = new ArrayList<String>();
        result.add(CommonFunctions.randomString(10));
        return result;
    }


    @ParameterizedTest
    @MethodSource("userNameProvider")
    void canRegisterUser(String userName){
        var email = String.format("%s@localhost", userName);
        var password = "password";
        //создать пользователя или адрес на почтовом сервере (JamesHelper)
         app.jamesApi().addUser(email,password);
        //заполняем форму создания и отправляем (браузер) - создать класс-помощник
        //app.user().startRegistration(userName, email);
        //создание пользователя через Rest
        app.rest().startRegistration(new UserData()
                .withUsername(userName)
                .withPassword(password)
                .withEmail(email)
        );

        //получаем (ждем) почту (MailHelper), извлекаем ссылку из письма
        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()){
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
            //проходим по ссылке и завершаем регистрацию пользователя (браузер)
            app.user().confirmRegistration(url,password);
            //проверяем, что пользователь может залогиниться (HttpSessionHelper)
            app.http().login(email, password);
        } else {
            throw new RuntimeException("No url in email");
        }
    }


}
