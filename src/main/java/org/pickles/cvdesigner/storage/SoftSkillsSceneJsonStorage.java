package org.pickles.cvdesigner.storage;

import org.pickles.cvdesigner.controllers.SoftSkillsSceneController;

public class SoftSkillsSceneJsonStorage extends JsonStorageTemplate {
     public SoftSkillsSceneJsonStorage() {
         super(SoftSkillsSceneController.class.getName());
     }
}
