const getHeaderData = (data, prefix) => {
    const textFieldSuffix = "TextField";
    const header = prefix + "BasicData1SceneController";
    const values = data[header];

    return {
        title: values["surname" + textFieldSuffix] + ", " + values["name" + textFieldSuffix],
        content: {
            telephone: values["telephone" + textFieldSuffix],
            email: values["email" + textFieldSuffix],
        }
    }
}

const getSoftData = (data, prefix) => {
    const soft = prefix + "SoftSkillsSceneController";
    const list = "softSkillsListView";
    const values = data[soft][list];

    const topicTextField = "topicTextField";
    const descriptionTextArea = "descriptionTextArea";

    let content = [];

    for (const valuesKey in values) {
        content.push({
            title: values[valuesKey][topicTextField],
            content: values[valuesKey][descriptionTextArea]
        })
    }

    return {
        title: "Soft Skills",
        content: content,
    }
}

const getHardData = (data, prefix) => {
    const soft = prefix + "HardSkillsSceneController";
    const list = "hardSkillsListView";
    const type = "hardSkillTypeRadioButtonToggleGroup";
    const values = data[soft][list];

    const topicTextField = "topicTextField";
    const descriptionTextArea = "descriptionTextArea";

    const certificates = "Certificates";
    const licenses = "Licences";
    const knowledge = "Specific knowledge";
    const others = "Others";

    let content = {};
    let insideData = {};

    content[certificates] = []
    content[licenses] = []
    content[knowledge] = []
    content[others] = []

    for (const valuesKey in values) {
        switch (values[valuesKey][type]) {
            case certificates:
                insideData["title"] = values[valuesKey][topicTextField];
                insideData["content"] = values[valuesKey][descriptionTextArea];
                content[certificates].push(insideData);
                insideData = {};
                break;
            case licenses:
                insideData["title"] = values[valuesKey][topicTextField];
                insideData["content"] = values[valuesKey][descriptionTextArea];
                content[licenses].push(insideData);
                insideData = {};
                break;
            case knowledge:
                insideData["title"] = values[valuesKey][topicTextField];
                insideData["content"] = values[valuesKey][descriptionTextArea];
                content[knowledge].push(insideData);
                insideData = {};
                break;
            case others:
                insideData["title"] = values[valuesKey][topicTextField];
                insideData["content"] = values[valuesKey][descriptionTextArea];
                content[others].push(insideData);
                insideData = {};
                break;
        }
    }

    return {
        title: "Hard Skills",
        content: content,
    }
}

const getEducationData = (data, prefix) => {
    const education = prefix + "EducationHistorySceneController";
    const list = "educationHistoryListView";
    const values = data[education][list];

    const countryTextField = "countryTextField";
    const fromDatePicker = "fromDatePicker";
    const toDatePicker = "toDatePicker";
    const schoolNameTextField = "schoolNameTextField";

    const fieldOfStudyTextField = "fieldOfStudyTextField";
    const degreeTextField = "degreeTextField";

    let content = [];

    for (const valuesKey in values) {
        content.push({
            title: values[valuesKey][fieldOfStudyTextField] + ", " + values[valuesKey][degreeTextField] + " degree",
            content: values[valuesKey][schoolNameTextField] + ", " + values[valuesKey][countryTextField] +
                ", from " + values[valuesKey][fromDatePicker] + " to " + values[valuesKey][toDatePicker]
        })
    }

    return {
        title: "Education History",
        content: content,
    }
}

const getJobData = (data, prefix) => {
    const job = prefix + "EmploymentHistorySceneController";
    const list = "employmentHistoryListView";
    const values = data[job][list];

    const nipTextField = "nipTextField";
    const addressTextField = "addressTextField";
    const fromDatePicker = "fromDatePicker";
    const toDatePicker = "toDatePicker";

    const positionTextField = "positionTextField";
    const companyNameTextField = "companyNameTextField";

    let content = [];

    for (const valuesKey in values) {
        content.push({
            title: values[valuesKey][companyNameTextField] + ", " + values[valuesKey][positionTextField] + " position",
            content: values[valuesKey][addressTextField] + ", NIP: " + values[valuesKey][nipTextField] +
                ", from " + values[valuesKey][fromDatePicker] + " to " + values[valuesKey][toDatePicker]
        })
    }

    return {
        title: "Employment History",
        content: content,
    }
}

const getInfoData = (data, prefix) => {
    const other = prefix + "OtherInfoSceneController";
    const otherInfoTextArea = "otherInfoTextArea";
    const valuesOther = data[other][otherInfoTextArea];

    const addressData = prefix + "BasicData2SceneController";
    const countryTextField = "countryTextField";
    const roadTextField = "roadTextField";
    const cityTextField = "cityTextField";
    const apartmentTextField = "apartmentTextField";
    const buildingTextField = "buildingTextField";
    const zipCodeTextField = "zipCodeTextField";
    const valuesAddress = data[addressData];
    const address = valuesAddress[countryTextField] + ", " + valuesAddress[cityTextField] + ", "
        + valuesAddress[roadTextField] + " " + valuesAddress[buildingTextField] + ", apartment "
        + valuesAddress[apartmentTextField] + ", " + valuesAddress[zipCodeTextField]

    const linksData = prefix + "HardSkillsSceneController";
    const list = "hardSkillsListView";
    const type = "hardSkillTypeRadioButtonToggleGroup";
    const topicTextField = "topicTextField";
    const descriptionTextArea = "descriptionTextArea";
    const linkToPortfolio = "Links to portfolio";
    const valuesLinks = data[linksData][list];

    let links = [];

    for (const valuesLinksKey in valuesLinks) {
        if (valuesLinks[valuesLinksKey][type] === linkToPortfolio) {
            links.push({
                title: valuesLinks[valuesLinksKey][topicTextField],
                content: valuesLinks[valuesLinksKey][descriptionTextArea]
            })
        }
    }

    let content = { other: valuesOther, Links: links, address: address}

    return {
        title: "Info",
        content: content,
    }
}

const getDataFromStorage = () => {
    let data = require("../storage.json");

    fetch("https://localhost:5000").then((res) => {
        data = res.json();
        return data;
    }).then((json) => {
        console.log(json)
    }).catch((err) => {
        console.log(err)
    })

    const prefix = "org.pickles.cvdesigner.controllers.";

    const footerStr = "I hereby give consent for my personal data included in the application to be" +
        " processed for the purposes of the recruitment process in accordance with Art. 6 paragraph 1 " +
        "letter a of the Regulation of the European Parliament and of the Council (EU) 2016/679 of 27 " +
        "April 2016 on the protection of natural persons with regard to the processing of personal data " +
        "and on the free movement of such data, and repealing Directive 95/46/EC (General Data Protection Regulation).";

    return {
        footer: {
            title: footerStr,
            content: footerStr,
        },
        header: getHeaderData(data, prefix),
        soft: getSoftData(data, prefix),
        hard: getHardData(data, prefix),
        info: getInfoData(data, prefix),
        education: getEducationData(data, prefix),
        job: getJobData(data, prefix),
    };
}

export default getDataFromStorage;
