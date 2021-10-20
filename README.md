# CV-Designer
An app that will make creating CV's easier

## Overview

CV Maker project is a colaborative project which will be developed by the "Pickles fellowship":
<ul>
  <li><a href="https://github.com/bkaryna">Karyna Babenko</a> - main Java App: Java, JavaFX, HTML, CSS, JS (maybe SQL)</li>
  <li><a href="https://github.com/Vyvr">Maciej Luciński</a> - frontend design (UI + UX), templates design, graphics design: HTML, CSS, JS, (graphical software of choice)</li>
  <li><a href="https://github.com/Szymonexis">Szymon Kaszuba-Gałka</a> - gitMaster, designer part of the application: HTML, CSS, JS, JavaFX, Java</li>
</ul>

Application will be developed with Java, HTML, CSS & JavaScript. JavaFX will be used for desktop app developement and Google Services and maybe Google Realtime Database (Firebase) or an SQL Databse will be put in place as well.

More info on Feature can be found in <a href="../Conception.md">Conception.md</a>

## Features
<ol>
  <li>Caches all sensitive data like names, surnames, date of birth, tel. no., education, job experiance etc.</li>
  <li>Provides desktop application which contains a JS-written <b>Designer</b> app within webview which:</li>
  <ol>
    <li>Supports a grid displayed over a sheet of paper (by default in A4 standard)</li>
    <li>Provides user with ready-to-use objects called panels (each panel will consist of a title and predefined components such as lists, different textfields and     pictureboxes):</li>
    <ul>
      <li>header panel</li>
      <li>CV profile picture panel</li>
      <li>contact info panel</li>
      <li>other info panel</li>
      <li>soft-skills panel</li>
      <li>hard-skills panel</li>
      <li>job experiance panel</li>
      <li>education panel</li>
      <li>others panel</li>
    </ul>
    <li>Designer will also allow user to insert pictures, set backgrounds and the style of the CV</li>
    <li>Components will be pre-filled and we plan on creating some ready to go designs (presets)</li>
    <li>Panels will be layed out on a static grid (A4 standard paper) - first app design considers 32x16 grid</li>
  </ol>
  <li>Provides user with an option to save his/hers created CV within a PDF format in a local path within the system (or within a database if this part of the project comes to be)</li>
  <li>Along side the developement of this application a ne proprietary format will be developed (based on a xml-like structure) which will be used within the designer application - this format will not leave the application itself as all CV's will be formatted upon "printing" or "finalizing" the project to a PDF format</li>
</ol>

## Optional features
If there is some spare time left, we might consider developing below features:
<ol>
  <li>CV-Designer format reversed parsing from PDF format</li>
  <li>Web version of the app</li>
  <li>SQL DB or RT DB will be put in place for online CV making and account creation</li>
  <li>Additional categories, templates, panels, etc.</li>
</ol>
