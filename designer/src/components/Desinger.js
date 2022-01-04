import React from "react";
import styled from "styled-components";

import Paper from "./Paper";
import getDataFromStorage from "../storage/StorageGetter"

const Container = styled.div`
  height: 813px;
  width: 575px;
  background-color: white;
`;

const Desinger = (props) => {
    const texts = getDataFromStorage();

    return (
        <Container>
            <Paper texts={texts} />
        </Container>
    );
};

export default Desinger;
