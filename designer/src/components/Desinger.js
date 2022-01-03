import React from "react";
import styled from "styled-components";

import Paper from "./Paper";

const Content = styled.div`
  background-color: #004aad;
  width: 1210px;
  height: 1210px;
  display:flex; 
  flex-direction:row;
`;

//742px should be the width
const Container = styled.div`
  height: 1210px;
  width: 800px;
  background-color: white;
  border: grey 1px dotted;
  border-radius: 16px;
`;

const Spacer = styled.div`
  width: 205px;
  border: grey 1px dotted;
  border-radius: 16px;
`;

const getHeaderData = (data, prefix) => {
    const textFieldSuffix = "TextField";
    const sexRadioButtonToggleGroup = "sexRadioButtonToggleGroup";
    const header = prefix + "BasicData1SceneController";
    const values = data[header];

    return {
        title: values["surname" + textFieldSuffix] + ", " + values["name" + textFieldSuffix],
        content: {
            telephone: values["telephone" + textFieldSuffix],
            email: values["email" + textFieldSuffix],
        }
    }
}

const getSoftData = (data, prefix) => {
    const soft = prefix + "SoftSkillsSceneController";
    const list = "softSkillsListView";
    const values = data[soft][list];

    return {
        title: "Soft Skills",
        content: values
    }
}

const getHardData = (data, prefix) => {
    const soft = prefix + "HardSkillsSceneController";
    const list = "hardSkillsListView";
    const values = data[soft][list];

    let content = {};

    for (const valuesKey in values) {
        if (valuesKey !== "") {
            console.log(values[valuesKey])
        }
    }

    return {
        title: "Hard Skills",
        content: content,
    }
}

const getDataFromStorage = () => {
    const data = require("../storage.json");

    const prefix = "org.pickles.cvdesigner.controllers.";

    const education = ["EducationHistorySceneController"];
    const job = ["EmploymentHistorySceneController"];
    const info = ["BasicData1SceneController", "BasicData2SceneController",
        "OtherInfoSceneController", "HardSkillsSceneController"];

    const footerStr = "I hereby give consent for my personal data included in the application to be" +
        " processed for the purposes of the recruitment process in accordance with Art. 6 paragraph 1 " +
        "letter a of the Regulation of the European Parliament and of the Council (EU) 2016/679 of 27 " +
        "April 2016 on the protection of natural persons with regard to the processing of personal data " +
        "and on the free movement of such data, and repealing Directive 95/46/EC (General Data Protection Regulation).";

    return {
        footer: {
            title: footerStr,
            content: footerStr,
        },
        header: getHeaderData(data, prefix),
        soft: getSoftData(data, prefix),
        hard: getHardData(data, prefix),
        info: "info",
        education: "education",
        job: "job",
    };
}

const Desinger = (props) => {
    const texts = getDataFromStorage();
    console.log(texts)

  return (
    <Content>
        <Spacer />
        <Container>
            <Paper texts={texts} />
        </Container>
        <Spacer />
    </Content>
  );
};

export default Desinger;
