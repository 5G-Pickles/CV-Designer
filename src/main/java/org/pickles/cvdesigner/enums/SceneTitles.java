package org.pickles.cvdesigner.enums;

public enum SceneTitles {
    START_TITLE("Hello :3"),
    BASIC_DATA_WINDOW_1_TITLE("Basic Data 1/2"),
    BASIC_DATA_WINDOW_2_TITLE("Basic Data 2/2"),
    EDUCATION_TITLE("Education History"),
    EMPLOYMENT_TITLE("Employment History"),
    HARD_SKILLS_TITLE("Hard Skills"),
    SOFT_SKILLS_TITLE("Soft Skills"),
    OTHER_INFO_TITLE("Other Info");

    public final String value;

    SceneTitles(String value) {
        this.value = value;
    }
}
