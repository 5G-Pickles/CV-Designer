import React from "react";
import styled from "styled-components";
import { useState } from "react";
import { useDrag } from "react-use-gesture";

const CardCss = styled.div`
  background-color: #004aad;
  position: relative;
  margin: 1rem;
  color: white;
  text-align: center;
  padding: 1rem;
  border: white 2px solid;
  border-radius: 16px;
`;

const Card = (props) => {
  const [cardPos, setCardPos] = useState({ x: 0, y: 0 });

  const bindCardPos = useDrag((params) => {
    setCardPos({
      x: params.offset[0],
      y: params.offset[1],
    });
  });

  return (
    <div
      {...bindCardPos()}
      style={{ position: "relative", top: cardPos.y, left: cardPos.x }}
    >
      <CardCss>{props.text}</CardCss>
    </div>
  );
};

export default Card;
