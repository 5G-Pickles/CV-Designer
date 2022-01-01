import React from "react";
import styled from "styled-components";

const Paper = (props) => {
  const PaperCss = styled.div`
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

  return <PaperCss>{props.text}</PaperCss>;
};

export default Paper;
