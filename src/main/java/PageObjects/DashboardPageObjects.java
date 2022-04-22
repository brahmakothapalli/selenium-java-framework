package PageObjects;

import org.openqa.selenium.By;

public final class DashboardPageObjects {


    /******************************************* MENU DETAILS *********************************/

    public static final By userDropdown = By.id("dropdownMenuLink");
    public static final By spinnerIcon = By.xpath("//div[@class='spinner-icon']");
    public static final By logoutLink = By.xpath("//a[@class='dropdown-item' and contains(text(), 'Logout')]");
    public static final By toggleBtnlick = By.xpath("//*[@id='toggle-btn']/i");
    public static final By searchPolicyLink = By.xpath("//a[@ptooltip='Search Policy']/span[contains(text(),'Search Policy')]");
    public static final By issuePolicyLink = By.id("issuepolicymodule");

    /******************************************* Motor MENU DETAILS *********************************/
    public static final By motorIssuePolicyLink = By.id("motorissuepolicymodule");
    public static final By motorSelfPolicyLink = By.xpath("//span[@id='motorselfpolicy']");
    public static final By motorBehalfPolicyLink = By.xpath("//span[@id='motorbehalfpolicy']");
    public static final By searchMotorPolicyLink = By.xpath("//a[@ptooltip='Search Motor Policy']/span[text()='Motor Policy']");
    public static final By searchMotorFireTheftPolicyLink = By.xpath("//a[@ptooltip='Search Motor Policy']/span[text()='Fire And Theft Policy']");
    public static final By quickQuoteLink = By.xpath("//span[text()='Quick Quote']");
    public static final By quickQuoteMotorCycleLink = By.xpath("//span[text()='Motor Cycle']");
    public static final By quickQuoteMotorCyclePackageLink = By.xpath("//span[text()='MotorCyclePackage']");
    public static final By quickQuoteMotorCycleLiabilityOnlyLink = By.xpath("//span[text()='MotorCycleLiabilityOnly']");
    public static final By quickQuotePrivatePassengerCarLink = By.xpath("//span[text()='Private Passenger Car']");
    public static final By quickQuoteVPC_ComprehensiveLink = By.xpath("//span[text()='VPC_Comprehensive']");
    public static final By quickQuoteCommercialVehicleLink = By.xpath("//span[text()='Commercial Vehicle']");
    public static final By quickQuoteCommercialGoodsLink = By.xpath("//span[text()='CommercialGoodsComprehensive']");
    public static final By quickQuotePassengerCarryingComprehensiveLink = By.xpath("//span[text()='PassengerCarryingComprehensive']");
    public static final By quickQuoteMiscellaneousVehiclePackageLink = By.xpath("//span[text()='MiscellaneousVehiclePackage']");
    public static final By planOptionDropdown = By.id("planOption");


    public static final By createInwardLink = By.xpath("//*[@id='side-main-menu']//span[text()='Create Inward']");


}
