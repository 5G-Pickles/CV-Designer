package org.pickles.cvdesigner.storage;

import org.pickles.cvdesigner.controllers.BasicData2SceneController;

public class BasicData2SceneJsonStorage extends JsonStorageTemplate {
    public BasicData2SceneJsonStorage() {
        super(BasicData2SceneController.class.getName());
    }
}
