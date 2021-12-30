import React from "react";
import styled from "styled-components";

import Paper from "./Paper";

const Content = styled.div`
  background-color: #004aad;
  padding: 1rem;
  maxHeight: 200px;
`;

const Cards = styled.div`
  margin: 1px;
  width: 25rem;
  padding: 1rem;
  border: grey 1px dotted;
  border-radius: 16px;
`;

const Desinger = (props) => {
  return (
    <Content>
        <Paper />
    </Content>
  );
};

export default Desinger;
