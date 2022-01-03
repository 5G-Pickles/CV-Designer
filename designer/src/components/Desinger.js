import React from "react";
import styled from "styled-components";

import Paper from "./Paper";
import data from "../storage.json";

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
    const header = ["BasicData1SceneController"];

    return {
        "name": data[prefix + header[0]]["surname" + textFieldSuffix] + ", " + data[prefix + header[0]]["name" + textFieldSuffix],
        "telephone": data[prefix + header[0]]["telephone" + textFieldSuffix],
        "email": data[prefix + header[0]]["email" + textFieldSuffix],
    }
}

const getDataFromStorage = () => {
    const data = require("../storage.json");
    console.log(data);

    const prefix = "org.pickles.cvdesigner.controllers.";

    const soft = ["SoftSkillsSceneController"];
    const hard = ["HardSkillsSceneController"];
    const education = ["EducationHistorySceneController"];
    const job = ["EmploymentHistorySceneController"];
    const info = ["BasicData1SceneController", "BasicData2SceneController",
        "OtherInfoSceneController", "HardSkillsSceneController"];



    const texts = {
        footer: "I hereby give consent for my personal data included in the application to be processed for the purposes of the recruitment process in accordance with Art. 6 paragraph 1 letter a of the Regulation of the European Parliament and of the Council (EU) 2016/679 of 27 April 2016 on the protection of natural persons with regard to the processing of personal data and on the free movement of such data, and repealing Directive 95/46/EC (General Data Protection Regulation).",
        header: getHeaderData(data, prefix),
        soft: "soft",
        hard: "hard",
        info: "info",
        education: "education",
        job: "job",
    }

    return texts;
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
