package org.pickles.cvdesigner.enums;

public enum SceneTitles {
    START_SCENE_TITLE("Hello :3"),
    BASIC_DATA_1_SCENE_TITLE("Basic Data 1/2"),
    BASIC_DATA_2_SCENE_TITLE("Basic Data 2/2"),
    EDUCATION_HISTORY_SCENE_TITLE("Education History"),
    EMPLOYMENT_HISTORY_SCENE_TITLE("Employment History"),
    HARD_SKILLS_SCENE_TITLE("Hard Skills"),
    SOFT_SKILLS_SCENE_TITLE("Soft Skills"),
    OTHER_INFO_SCENE_TITLE("Other Info"),
    DESIGNER_SCENE_TITLE("Designer");

    public final String value;

    SceneTitles(String value) {
        this.value = value;
    }
}
