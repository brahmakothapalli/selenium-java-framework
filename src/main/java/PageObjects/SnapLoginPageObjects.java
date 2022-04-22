package PageObjects;

import org.openqa.selenium.By;

public class SnapLoginPageObjects {

    private SnapLoginPageObjects(){}

    public static final By letsGoButton = By.xpath("//a[@class='letsgo']");

    public static final By  userName = By.name("loginfmt");

    public static final By  nextButton = By.xpath("//input[@value='Next']");

    public static final By  password = By.id("okta-signin-password");

    public static final By  userId = By.id("okta-signin-username");

    public static final By  answerField = By.xpath("//input[@placeholder='Answer']");

    public static final By  signInButton = By.id("okta-signin-submit");

    public static final By  noButton = By.xpath("//input[@value='No']");

    public static final By  userErrorMsg = By.id("usernameError");

    public static final By  passwordErrorMsg = By.xpath("//p[text()='Unable to sign in']");

}
