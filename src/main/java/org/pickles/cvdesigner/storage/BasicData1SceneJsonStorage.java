package org.pickles.cvdesigner.storage;

import org.pickles.cvdesigner.controllers.BasicData1SceneController;

public class BasicData1SceneJsonStorage extends JsonStorageTemplate {
    public BasicData1SceneJsonStorage() {
        super(BasicData1SceneController.class.getName());
    }
}
