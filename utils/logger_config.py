import logging
import sys


class LoggerConfiguration:

    @staticmethod
    def get_logger():
        print("\n File Path: ", sys.path[0] + "\\logs\\report.log")
        logging.basicConfig(filename=sys.path[0] + "\\logs\\report.log",
                            format='%(asctime)s - %(levelname)s - %(message)s',
                            level=logging.INFO)
        logger_instance = logging.getLogger()
        return logger_instance
