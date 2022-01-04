import React from "react";
import RGL, { WidthProvider } from "react-grid-layout";

import "./Paper.css";
import GridElement from "./GridElement";

const ReactGridLayout = WidthProvider(RGL);

class Paper extends React.PureComponent {
    static defaultProps = {
        isDraggable: true,
        isResizable: true,
        rowHeight: 30,
        preventCollision: false,
        cols: 13,
        compactType: null,
    };

    height = 17;

    state = {
        layout: [
            { x: 0, y: 0, w: 13, h: 3, i: "header" },
            { x: 0, y: 2, w: 3, h: 15, i: "info" },
            { x: 3, y: 3, w: 10, h: 4, i: "education" },
            { x: 3, y: 7, w: 10, h: 4, i: "job" },
            { x: 3, y: 11, w: 5, h: 7, i: "hard" },
            { x: 8, y: 11, w: 5, h: 7, i: "soft" },
            { x: 0, y: 18, w: 13, h: 2, i: "footer", static: true }
        ]
    };

    render() {
        return (
            <React.Fragment>
                <ReactGridLayout
                    {...this.props}
                    onLayoutChange={(layout) => {
                        layout.forEach((item) => {
                            if (item.y > this.height && item.i !== "footer") {
                                item.y = this.height;
                            }
                            if (item.h + item.y > this.height && item.i !== "footer") {
                                item.h = this.height - item.y + 1
                            }
                        })
                        this.setState({layout})
                    }}
                >
                    {console.log(this.props.texts)}
                    {console.log(this.state.layout)}
                    {this.state.layout.map((item) => (
                        <div key={item.i} data-grid={item}>
                            <GridElement data={this.props.texts[item.i]} type={item.i} />
                        </div>
                    ))}
                </ReactGridLayout>
            </React.Fragment>
        );
    }
}

export default Paper;
