""" sample python tests"""
import time
import pytest
from selenium.webdriver.common.by import By


class TestValidLoginPageCases:
    """ login page valid test cases"""

    @pytest.mark.smoke
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
