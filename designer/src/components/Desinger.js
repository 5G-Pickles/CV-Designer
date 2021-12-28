import React from "react";
import styled from "styled-components";

import Card from "./Card";
import Paper from "./Paper";
import RndCard from "./RndCard";

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


  
  return (
    <Content>
      <Cards>
        <Card text="text" />
        <Card text="text" />
        <Card text="text" />
        <Card text="text" />
        <Card text="text" />
        <Card text="text" />
        <div id="dupa">
          <RndCard id="dupa" text="text" />
        </div>
      </Cards>
      <Paper text="pepe" />
    </Content>
    // <RndCard text="text" />
  );
};

export default Desinger;
