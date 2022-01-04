package org.pickles.cvdesigner.enums;

public enum SceneSizes {
    MAIN_WIDTH(900),
    MAIN_HEIGHT(600),
    WEB_VIEW_WIDTH(598),
    WEB_VIEW_HEIGHT(924);

    public final Integer value;

    SceneSizes(Integer value) {
        this.value = value;
    }
}

