package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BrowserType {
    CHROME("CHROME"),
    FIREFOX("FIREFOX"),
    EDGE("EDGE");

    private final String browser;

}
