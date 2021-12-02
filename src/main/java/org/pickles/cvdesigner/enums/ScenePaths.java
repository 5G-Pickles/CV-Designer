package org.pickles.cvdesigner.enums;

public enum ScenePaths {
    START_SCENE("fxml/01startScene.fxml"),
    BASIC_DATA_1_SCENE("fxml/02basicData1Scene.fxml"),
    BASIC_DATA_2_SCENE("fxml/03basicData2Scene.fxml"),
    EDUCATION_SCENE("fxml/04educationHistoryScene.fxml"),
    EMPLOYMENT_SCENE("fxml/05employmentHistoryScene.fxml"),
    HARD_SKILLS_SCENE("fxml/06hardSkillsScene.fxml"),
    SOFT_SKILLS_SCENE("fxml/07softSkillsScene.fxml"),
    OTHER_INFO_SCENE("fxml/08otherInfoScene.fxml");

    public final String value;

    ScenePaths(String value) {
        this.value = value;
    }
}
