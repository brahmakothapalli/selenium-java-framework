from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import pytest


@pytest.fixture()
def get_driver():
    print("****** initialising the browser driver *****")
    chrome_options = Options()
    chrome_options.add_argument('--ignore-certificate-errors')
    chrome_options.add_experimental_option('excludeSwitches', ['enable-logging'])
    driver = webdriver.Chrome(options=chrome_options)
    driver.maximize_window()
    driver.implicitly_wait(30)
    driver.delete_all_cookies()
    driver.get("https://practicetestautomation.com/practice-test-login/")
    yield driver
    print("****** closing the browser driver ********")
    driver.quit()
