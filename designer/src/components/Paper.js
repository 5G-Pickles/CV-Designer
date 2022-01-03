import React from "react";
import RGL, { WidthProvider } from "react-grid-layout";

import "./styles.css";

const ReactGridLayout = WidthProvider(RGL);

class Paper extends React.PureComponent {
    static defaultProps = {
        isDraggable: true,
        isResizable: true,
        rowHeight: 50,
        preventCollision: false,
        cols: 12,
        compactType: null,
    };

    height = 17;

    state = {
        layout: [
            { x: 0, y: 0, w: 12, h: 2, i: "header" },
            { x: 0, y: 1, w: 3, h: 16, i: "info" },
            { x: 3, y: 2, w: 9, h: 4, i: "education" },
            { x: 3, y: 6, w: 9, h: 4, i: "job" },
            { x: 3, y: 10, w: 9, h: 4, i: "hard" },
            { x: 3, y: 14, w: 9, h: 4, i: "soft" },
            { x: 0, y: 18, w: 12, h: 2, i: "footer", static: true }
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
                    {this.state.layout.map((item) => (
                        <div key={item.i} data-grid={item}>
                            <span>{this.props.texts[item.i].title}</span>
                        </div>
                    ))}
                </ReactGridLayout>
            </React.Fragment>
        );
    }
}

export default Paper;
