import React from "react";
import { sha256 } from "js-sha256";

import "./GridElement.css";

const TitledUnorderedList = (props) => {
    return <React.Fragment>
        {props.content[props.name].length !== 0 && <div>
            <h4>{props.name}:</h4>
            <ul>
                {props.content[props.name].map((item) => (
                    <li key={sha256(item.title + item.content)}>
                        <b>{item.title}</b> {item.content.length !== 0 && (" - " + item.content)}
                    </li>
                ))}
            </ul>
        </div>}
    </React.Fragment>
}

const UnorderedList = (props) => {
    return <React.Fragment>
        <div className={props.type + "__title"}>
            <h2 className={props.type}>{props.data.title}</h2>
        </div>
        <div className={props.type + "__content"}>
            <ul>
                {props.data.content.map((item) => (
                    <li key={sha256(item.title + item.content)}>
                        <b>{item.title}</b> {item.content.length !== 0 && " - " + item.content.toString()}
                    </li>
                ))}
            </ul>
        </div>
    </React.Fragment>
}

const GridElement = (props) => {
    const footer = "footer";
    const header = "header";
    const soft = "soft";
    const hard = "hard";
    const info = "info";
    const job = "job";
    const education = "education";

    switch (props.type) {
        case footer:
            return <div className={props.type + "__title"}>
                <span className={footer}>{props.data.title}</span>
            </div>

        case header:
            return <React.Fragment>
                <div className={props.type + "__title"}>
                    <h1 className={props.type}>{props.data.title}</h1>
                </div>
                <div className={props.type + "__content"}>
                    <h3 className={props.type}>Telephone: {props.data.content.telephone}</h3>
                    <h3 className={props.type}>
                        Email: {props.data.content.email}
                    </h3>
                </div>
            </React.Fragment>;

        case soft:
            return <UnorderedList type={props.type} data={props.data} />

        case hard:
            return <React.Fragment>
                <div className={props.type + "__title"}>
                    <h2 className={props.type}>{props.data.title}</h2>
                </div>
                <div className={props.type + "__content"}>
                    <TitledUnorderedList name={"Certificates"} content={props.data.content}/>
                    <TitledUnorderedList name={"Licences"} content={props.data.content}/>
                    <TitledUnorderedList name={"Specific knowledge"} content={props.data.content}/>
                    <TitledUnorderedList name={"Others"} content={props.data.content}/>
                </div>
            </React.Fragment>

        case education:
            return <UnorderedList type={props.type} data={props.data} />

        case job:
            return <UnorderedList type={props.type} data={props.data} />

        case info:
            return <React.Fragment>
                <div className={props.type + "__title"}>
                    <h2 className={props.type}>{props.data.title}</h2>
                </div>
                <div className={props.type + "__content"}>
                    <h4>Address: </h4>
                    <span>{props.data.content.address}</span>
                    <span />
                    <TitledUnorderedList name={"Links"} content={props.data.content} />
                    <span />
                    <h4>Hobbies and other:</h4>
                    <span>{props.data.content.other}</span>
                </div>
            </React.Fragment>

        default:
            return <span><h1>ERROR</h1></span>
    }
}

export default GridElement;