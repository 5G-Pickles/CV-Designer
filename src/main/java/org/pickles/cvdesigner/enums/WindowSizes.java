package org.pickles.cvdesigner.enums;

public enum WindowSizes {
    MAIN_WINDOW_WIDTH(320),
    MAIN_WINDOW_HEIGHT(240),
    WEB_VIEW_WIDTH(1000),
    WEB_VIEW_HEIGHT(1000);

    public final Integer value;

    WindowSizes(Integer value) {
        this.value = value;
    }
}

