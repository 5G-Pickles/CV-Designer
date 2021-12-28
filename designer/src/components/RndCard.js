import React from "react";
import styled from "styled-components";
import { Rnd } from "react-rnd";

const style = {
  background: "#004aad",
  position: "relative",
  margin: "1rem",
  color: "white",
  text: "center",
  padding: "1rem",
  border: "white 2px solid",
  borderRadius: "16px",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
};

class RndCard extends React.Component {
  constructor() {
    super();
    this.state = {
      x: 0,
      y: 0,
    };
  }

  render() {
    const text = this.props.text;

    // const thisEl = document.getElementById("dupa");
    // console.log(thisEl);
    // const boundries = thisEl.getBoundingClientRect();
    // this.state.x = boundries.offsetLeft;
    // this.state.y = boundries.offsetTop;
    return (
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
          {text}
        </Rnd>
    );
  }
}

export default RndCard;
