package org.pickles.cvdesigner.storage;

import org.pickles.cvdesigner.controllers.EmploymentHistorySceneController;

public class EmploymentHistorySceneJsonStorage extends JsonStorageTemplate{
    public EmploymentHistorySceneJsonStorage() {
        super(EmploymentHistorySceneController.class.getName());
    }
}
