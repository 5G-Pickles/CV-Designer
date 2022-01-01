import GridLayout from 'react-grid-layout';
import React from "react";
import styled from "styled-components";

class MyFirstGrid extends React.Component {
    render() {
        // layout is an array of objects, see the demo for more complete usage
        const layout = [
            {i: 'a', x: 0, y: 0, w: 1, h: 2, static: true},
            {i: 'b', x: 1, y: 0, w: 3, h: 2, minW: 2, maxW: 4},
            {i: 'c', x: 4, y: 0, w: 1, h: 2}
        ];

        const Card = styled.div`
            background-color: #004aad;
              color: white;
              text-align: center;
              padding: 1rem;
              margin: 1rem;
              border: white 2px solid;
              border-radius: 16px;
        `;

        return (
            <GridLayout className="layout" layout={layout} cols={12} rows={10} rowHeight={30} width={1200} margin={[100, 100]}>
                <Card key={"b"}>text</Card>
                <Card key={"c"}>text</Card>
            </GridLayout>
        )
    }
}

export default MyFirstGrid