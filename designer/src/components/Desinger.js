import React from "react";
import styled from "styled-components";

import Paper from "./Paper";
import RndCard from "./RndCard";
import Card from "./RndCard";

const Content = styled.div`
  background-color: #004aad;
  padding: 1rem;
  width: 100%;
  height: 100%;
  display: flex;
`;

const Cards = styled.div`
  margin: 1px;
  width: 25rem;
  padding: 1rem;
  border: grey 1px dotted;
  border-radius: 16px;
`;

const Desinger = (props) => {
  const sampleText =
    "What is this?\n\nIt's a meme Batman...\nI don't understand...";

  return (
    <Content>
      <Cards>
        <RndCard>
          <h2>Header</h2>
        </RndCard>
        <RndCard>
          <h2>Soft Skills</h2>
        </RndCard>
        <RndCard>
          <h2>Hard Skills</h2>
        </RndCard>
        <RndCard>
          <h2>Employment History</h2>
        </RndCard>
        <RndCard>
          <h2>Education History</h2>
        </RndCard>
        <RndCard>
          <h2>Other info</h2>
        </RndCard>
        <RndCard>
          <h2>Footer</h2>
        </RndCard>
      </Cards>
      <Paper text="pepe" />
    </Content>
    // <RndCard text="text" />
  );
};

export default Desinger;
