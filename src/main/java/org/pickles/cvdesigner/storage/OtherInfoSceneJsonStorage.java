package org.pickles.cvdesigner.storage;

import org.pickles.cvdesigner.controllers.OtherInfoSceneController;

public class OtherInfoSceneJsonStorage extends JsonStorageTemplate {
    public OtherInfoSceneJsonStorage() {
        super(OtherInfoSceneController.class.getName());
    }
}
