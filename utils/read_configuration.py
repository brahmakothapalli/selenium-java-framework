import configparser
import sys

config = configparser.RawConfigParser()
config.read(sys.path[0] + "\\pytest.ini")


class ReadConfiguration:

    @staticmethod
    def get_url():
        return config.get('config data', 'base_url')

    @staticmethod
    def get_username():
        return config.get('config data', 'user_name')

    @staticmethod
    def get_password():
        return config.get('config data', 'password')
