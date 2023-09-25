""" login page objects"""
from selenium.webdriver.common.by import By

from pageobjects.base_page import BasePage


class LoginPage(BasePage):
    __username_field = (By.ID, "username")
    __password_field = (By.ID, "password")
    __login_button = (By.ID, "submit")
    __login_success_message = (By.TAG_NAME, "h1")
    __logout_button = (By.LINK_TEXT, 'Log out')
    __error_message_text = (By.ID, "error")

    def __init__(self, driver):
        super().__init__(driver)

    def login_application(self, username, password):
        super().wait_for_element_visibility(self.__username_field)
        super().enter_text(self.__username_field, username)
        super().enter_text(self.__password_field, password)
        super().click_element(self.__login_button)

    def get_login_success_message(self) -> str:
        super().wait_for_element_visibility(self.__login_success_message)
        return super().get_text(self.__login_success_message)

    def click_logout(self):
        super().click_element(self.__logout_button)

    def get_error_message(self) -> str:
        return super().get_text(self.__error_message_text)
