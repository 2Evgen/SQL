package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification']");


    public void verificationPageVisibility() {
        codeField.shouldBe(visible);
    }

    public void validVerify(String verificationCode) {
        verify(verificationCode);
        page(DashboardPage.class);
    }

    public void verify(String verificationCode){
        codeField.setValue(verificationCode);
        verifyButton.click();
    }
}