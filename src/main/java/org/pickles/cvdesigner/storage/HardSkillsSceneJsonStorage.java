package org.pickles.cvdesigner.storage;

import org.pickles.cvdesigner.controllers.HardSkillsSceneController;

public class HardSkillsSceneJsonStorage extends JsonStorageTemplate {
    public HardSkillsSceneJsonStorage() {
        super(HardSkillsSceneController.class.getName());
    }
}
