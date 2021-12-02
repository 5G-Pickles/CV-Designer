module org.pickles.cvdesigner {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.gluonhq.charm.glisten;
    requires commons.validator;
    requires libphonenumber;
    requires google.maps.services;
    requires json.simple;
    requires gson;
    requires java.sql;
    requires java.desktop;

    opens org.pickles.cvdesigner to javafx.fxml;
    exports org.pickles.cvdesigner;
    exports org.pickles.cvdesigner.controllers;
    opens org.pickles.cvdesigner.controllers to javafx.fxml;
}