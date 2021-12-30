import React from "react";
import RGL, { WidthProvider } from "react-grid-layout";

import "./styles.css";

const ReactGridLayout = WidthProvider(RGL);

class Paper extends React.PureComponent {
    static defaultProps = {
        isDraggable: true,
        isResizable: true,
        items: 5,
        width: 800,
        rowHeight: 50,
        preventCollision: false,
        cols: 24,
        compactType: null,
        // resizeHandles: ['s', 'w', 'e', 'n', 'sw', 'nw', 'se', 'ne'],
    };

    state = {
        layout: [
            { x: 0, y: 0, w: 1, h: 1, i: "test1" },
            { x: 0, y: 1, w: 1, h: 1, i: "test2" }
        ]
    };

    render() {
        return (
            <React.Fragment>
                <ReactGridLayout
                    {...this.props}
                    onLayoutChange={(layout) => this.setState({ layout })}
                >
                    {this.state.layout.map((item) => (
                        <div key={item.i} data-grid={item}>
                            <span>{item.i}</span>
                        </div>
                    ))}
                </ReactGridLayout>
            </React.Fragment>
        );
    }
}

export default Paper;
