# Conception
This file contains the overall written scheme for the JavaFX part of the application

## First scene - "Hello"
Consists of the logo of the app and a <b>Start</b> button - in the future might also consist of the <b>Login</b> button and <b>Register</b> button.

## Second scene - "Basic data"
Consists of <b>basic data</b> inputs:
<ul>
	<li>Name - input text box - with not-needed UTF-8 characters removal (only alphabet accepted)</li>
	<li>Surname - input text box - with not-needed UTF-8 characters removal (only alphabet accepted)</li>
	<li>Telephone number - input text box - checks if the telephone number is in Polish format (9 digits), accepts the number in space or dash independet forms, only numbers</li>
	<li>E-mail address - input text box - checks if e-mail address is correct (format <i>address@domain.com</i>)</li>
	<ul>
		<li>check box "Send confirmation e-mail" - when checked an e-mail will be sent to the provided e-mail address (optional 6 digit code verification)</li>
	</ul>
	<li>Sex - input radial buttons - options: "Male", "Female", "Other"</li>
	<li><b>Next</b> button - only works when all data is correct and moves user to the next scene</li>
	<li><b>Back arrow</b> - takes user to the previous scene</li>
</ul>

## Third scene - "Basic data"
Consits of <b>basic data</b> inputs - all of the below data is parsed and showed within MapView upon clicking the <b>Confirm</b> button:
<ul>
	<li>Country  - input text box - defaults to Poland - at first only option, in the future might be changed to support more countries</li>
	<li>City - input text box - at first only Polish cities supported, in future more countries might get support</li>
	<li>Road - input text box - only alphabet</li>
	<li>Building number - input text box - only alphanumeric</li>
	<li>Apartment number - input text box - only numeric</li>
	<li>ZIP code - input text box - first only Polish ZIP code formats supported in XXXXX or XX-XXX or XX XXX format</li>
	<li>MapView - half of the stage is taken for this component, uses GoogleMaps API to display the given and parsed address data upon clicking the <b>Confirm</b> button </li>
	<li><b>Next</b> button - only works when all data is correct and moves user to the next scene</li>
	<li><b>Back arrow</b> - takes user to the previous scene</li>
</ul>

## Fourth scene - "Education history"
Consists of <b>previous education data</b> inputs - each <b>education history</b> entry is a seperate scene - this scene can be skipped:
<ul>
	<li>List view - shows all created <b>education history</b> entries - placed on the left of the scene. List entries will be identified by <b>school name</b> (in bold)</li>
	<li>Name of the school/university - input text box</li>
	<li>Address of the school/university (optional) - input text box - Country (required), City (optional), Road (optional)</li>
	<li>Field of study - input text box - defaults to "General"</li>
	<li>Deegree - input text box </li>
	<li>Start date - input date </li>
	<li>End date (optional) - input text box - defaults to "Today"</li>
	<li><b>+</b> button - to add another <b>education history</b> entry</li>
	<li><b>Next</b> button - only works when all data is correct and moves user to the next scene</li>
	<li><b>Back arrow</b> - takes user to the previous scene</li>
</ul>

## Fifth scene - "Employment history"
Consists of <b>previous employment data</b> inputs - each <b>employment history</b> entry is a seperate scene - this scene can be skipped:
<ul>
	<li>List view - shows all created <b>employment history</b> entries - placed on the left of the scene. List entries will be identified by <b>company name</b> (in bold) and <b>position taken</b> (in smaller font, below company name)</li>
	<li>Name of the company - input text box</li>
	<li>Address of the company (optional) - input text box - Country (required), City (optional), Road (optional)</li>
	<li>NIP number (optional) - input text box</li>
	<li>Position within the company - input text box</li>
	<li>Start date - input date </li>
	<li>End date (optional) - input text box - defaults to string "Today"</li>
	<li><b>+</b> button - to add another <b>employment history</b> entry</li>
	<li><b>Next</b> button - only works when all data is correct and moves user to the next scene</li>
	<li><b>Back arrow</b> - takes user to the previous scene</li>
</ul>

## Sixth scene - "Hard skills"
Consists of <b>hard skills data</b> inputs - each <b>hard skills</b> entry is an item on a list view. <b>Hard skills</b> entries can be sorted within the list view into 4 pre-specified groups:
<ul>
	<li>certificates - if selected then:</li>
	<ul>
		<li>topic text box changes its float text to "Certificate name"</li>
		<li>description text box changes its float text to "Certificate description"</li>
	</ul>
	<li>licenses - if selected then:</li>
	<ul>
		<li>topic text box changes its float text to "License name"</li>
		<li>description text box changes its float text to "License description"</li>
	</ul>
	<li>field specific knowledge - if selected then:</li>
	<ul>
		<li>topic text box changes its float text to "Knowledge name"</li>
		<li>description text box changes its float text to "Knowledge description"</li>
	</ul>
	<li>links to portfolio - if selected then:</li>
	<ul>
		<li>topic text box changes its float text to "Link"</li>
		<li>description text box changes its float text to "Link description"</li>
	</ul>
	<li>others - if selected then:</li>
	<ul>
		<li>topic text box float text is "Topic"</li>
		<li>description text box float text is "Description"</li>
	</ul>
</ul>
These groups will be avalible for the user to be checked on the bottom of the scene as radial/check boxes and will be visible as static root elements within list view - this scene can be skipped:
<ul>
	<li>List view - shows all created <b>hard skills</b> entries and their placement within specified root category - placed on the left of the scene. List entries will be identified by <b>topic of the hard skill</b> (in bold)</li>
	<li>Topic of the hard skill entry - input text box</li>
	<li>Additional description (optional) - input text box</li>
	<li><b>+</b> button - to add another <b>hard skills</b> entry</li>
	<li><b>Next</b> button - only works when all data is correct and moves user to the next scene</li>
	<li><b>Back arrow</b> - takes user to the previous scene</li>
</ul>

## Seventh scene - "Soft skills"
Consists of <b>soft skills data</b> inputs - each <b>soft skills</b> entry is an item on a list view - this scene can be skipped:
<ul>
	<li>List view - shows all created <b>soft skills</b> entries and is placed on the left of the scene. List entries will be identified by <b>topic of the soft skill</b> (in bold)</li>
	<li>Topic of the soft skill entry - input text box</li>
	<li>Additional description (optional) - input text box</li>
	<li><b>+</b> button - to add another <b>soft skills</b> entry</li>
	<li><b>Next</b> button - only works when all data is correct and moves user to the next scene</li>
	<li><b>Back arrow</b> - takes user to the previous scene</li>
</ul>

## Eight scene - "Other"
Consists of the <b>other data</b> input - this window is a very basic text editor - supports up to 500 characters. Supports following styling options:
<ul>
	<li>bold</li>
	<li>italic</li>
	<li>underline</li>
	<li>unordered list</li>
	<li>ordered list</li>
</ul>

## Nineth scene - "Designer"
Consists of a webView component which launches the <b>Designer</b> application. TBD




























