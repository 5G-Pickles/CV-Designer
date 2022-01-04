import React from "react";
import styled from "styled-components";

import Paper from "./Paper";
import getDataFromStorage from "../storage/StorageGetter"

const Content = styled.div`
  background-color: #004aad;
  width: 857px;
  height: 1210px;
  display:flex; 
  flex-direction:row;
`;

const Container = styled.div`
  height: 1210px;
  width: 857px;
  background-color: white;
  border: grey 1px dotted;
  border-radius: 16px;
`;

const Desinger = (props) => {
    const texts = getDataFromStorage();

    return (
        <Content>
            <Container>
                <Paper texts={texts} />
            </Container>
        </Content>
    );
};

export default Desinger;
