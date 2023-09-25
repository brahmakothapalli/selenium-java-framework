from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec


class BasePage:

    def __init__(self, driver: WebDriver) -> None:
        self.driver = driver

    def find(self, locator) -> WebElement:
        return self.driver.find_element(*locator)

    def click_element(self, locator):
        self.find(locator).click()

    def enter_text(self, locator, text):
        self.find(locator).send_keys(text)

    def get_text(self, locator) -> str:
        return self.find(locator).text

    def wait_for_element_visibility(self, locator):
        wait = WebDriverWait(self.driver, timeout=10, poll_frequency=1)
        wait.until(ec.visibility_of_element_located(locator))

    def current_url(self):
        return self.driver.current_url

    def take_screenshot(self):
        self.driver.get_screenshot_as_file("screenshot.png")

    def title(self):
        return self.driver.title
