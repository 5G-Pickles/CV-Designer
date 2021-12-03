package org.pickles.cvdesigner.storage;

import org.pickles.cvdesigner.controllers.EducationHistorySceneController;

public class EducationHistorySceneJsonStorage extends JsonStorageTemplate {
    public EducationHistorySceneJsonStorage() {
        super(EducationHistorySceneController.class.getName());
    }
}
