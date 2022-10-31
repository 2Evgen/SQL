package ru.netology.test;

import com.codeborne.selenide.Selenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class BankLoginTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @AfterAll
    static void cleanDatabase() {
        SQLHelper.clean();
    }

    @Test
    void shouldLogIn() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.validVerify(SQLHelper.getCode());
    }

    @Test
    void shouldGetBlockMessage() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfoWithInvalid();
        loginPage.login(authInfo);
        loginPage.getInvalidLoginOrPassword();
        loginPage.cleaning();
        loginPage.login(authInfo);
        loginPage.getInvalidLoginOrPassword();
        loginPage.cleaning();
        loginPage.login(authInfo);
        loginPage.getBlockedMessage();
    }
}
