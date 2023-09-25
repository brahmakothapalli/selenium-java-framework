""" sample python tests"""
import time
import pytest
from selenium.webdriver.common.by import By

from pageobjects.login_page_objects import LoginPage


class TestParameterizedCases:
    """ login page tests"""

    @pytest.mark.login
    @pytest.mark.parametrize("scenario, username, password, expected_message",
                             [("valid", "student", "Password123", "Logged In Successfully"),
                              ("invalid", "student", "wrongpass", "Your password is invalid!")])
    def test_login_parameterization(self, get_driver, scenario, username, password, expected_message):
        """validating login functionality"""
        login_page = LoginPage(get_driver)
        login_page.login_application(username, password)
        login_page.take_screenshot()
        if scenario == "valid":
            success_message = login_page.get_login_success_message()
            assert success_message == expected_message, "login success message is not displayed"
            actual_url = login_page.current_url()
            assert "logged-in-successfully" in actual_url
            login_page.click_logout()
        else:
            time.sleep(3)
            error_message = login_page.get_error_message()
            assert error_message == expected_message, "error message not displayed"
            print("***** Test successfully executed :: test_login_positive *****")

    @pytest.mark.login
    def test_print_url(self, get_driver):
        """validating login functionality"""
        login_page = LoginPage(get_driver)
        print(login_page.current_url())

    @pytest.mark.login
    def test_print_title(self, get_driver):
        """validating login functionality"""
        login_page = LoginPage(get_driver)
        print(login_page.title())
