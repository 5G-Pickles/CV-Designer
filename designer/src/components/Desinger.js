import React from "react";
import styled from "styled-components";
import { useState } from "react";
import { useDrag } from "react-use-gesture";

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

const Card = styled.div`
  position: relative;
  margin: 1rem;
  color: white;
  text-align: center;
  padding: 1rem;
  border: white 2px solid;
  border-radius: 16px;
`;

const Paper = styled.div`
  margin: 0 1rem 0 1rem;
  background-color: white;
  display: grid;
  grid-template-columns: 1fr;
  grid-auto-columns: 50%;
  grid-auto-flow: column;
  height: 106rem;
  width: 75rem;
  border-radius: 16px;
`;

const Desinger = () => {
  const [cardPos, setCardPos] = useState({ x: 0, y: 0 });
  const bindCardPos = useDrag((params) => {
    setCardPos({
      x: params.offset[0],
      y: params.offset[1],
    });
  });

  return (
    <Content>
      <Cards>
        <div
          {...bindCardPos()}
          style={{ position: "relative", top: cardPos.y, left: cardPos.x }}
        >
          <Card>Text</Card>
        </div>
        <Card>Text</Card>
        <Card>Text</Card>
        <Card>Text</Card>
        <Card>Text</Card>
      </Cards>
      <Paper>pepe</Paper>
    </Content>
  );
};

export default Desinger;
