import React from "react";
import styled from "styled-components";
import { Rnd } from "react-rnd";

const style = {
  background: "#004aad",
  position: "relative",
  color: "white",
  text: "center",
  padding: "1rem",
  border: "white 2px solid",
  borderRadius: "16px",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
};

const RndDiv = styled.div`
  margin: 1rem;
`;

class RndCard extends React.Component {
  constructor() {
    super();
    this.state = {
      x: 0,
      y: 0,
    };
  }

  render() {
    return (
      <RndDiv>
        <Rnd
          style={style}
          position={{ x: this.state.x, y: this.state.y }}
          onDragStop={(e, d) => {
            this.setState({ x: d.x, y: d.y });
          }}
          onResizeStop={(e, direction, ref, delta, position) => {
            this.setState({
              width: ref.style.width,
              height: ref.style.height,
              ...position,
            });
          }}
        >
          {this.props.children}
        </Rnd>
      </RndDiv>
    );
  }
}

export default RndCard;
