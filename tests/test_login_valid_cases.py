""" sample python tests"""
import time
import pytest
from selenium.webdriver.common.by import By


class TestValidLoginPageCases:
    """ login page tests"""

    @pytest.mark.login
    def test_login_positive(self, get_driver):
        """validating login functionality"""
        user_name_field = get_driver.find_element(By.ID, "username")
        password_field = get_driver.find_element(By.ID, "password")
        submit_button = get_driver.find_element(By.ID, "submit")
        user_name_field.send_keys("student")
        password_field.send_keys("Password123")
        submit_button.click()
        message = get_driver.find_element(By.TAG_NAME, "h1")
        success_message = message.text
        assert success_message == "Logged In Successfully"
        actual_url = get_driver.current_url
        assert "logged-in-successfully" in actual_url
        log_out_button = get_driver.find_element(By.LINK_TEXT, 'Log out')
        assert log_out_button.is_displayed(), "logout button not displayed"
        print("***** Test successfully executed :: test_login_positive *****")

    @pytest.mark.login
    def test_login_negative(self, get_driver):
        """validating login functionality"""
        user_name_field = get_driver.find_element(By.ID, "username")
        password_field = get_driver.find_element(By.ID, "password")
        submit_button = get_driver.find_element(By.ID, "submit")
        user_name_field.send_keys("student")
        password_field.send_keys("wrongpassword")
        submit_button.click()
        time.sleep(3)
        error = get_driver.find_element(By.ID, "error")
        error_message = error.text
        assert error_message.strip() == "Your password is invalid!"
        print("***** Test successfully executed :: test_login_negative *****")
