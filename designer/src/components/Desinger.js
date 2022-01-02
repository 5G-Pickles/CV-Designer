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

const Desinger = (props) => {
    const data = require("../storage.json");
    console.log(data);

    const texts = {
        "footer": "footer",
        "header": "header",
        "soft": "soft",
        "hard": "hard",
        "info": "info",
        "education": "education",
        "job": "job",

    }
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
