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

## Fourth scene - "Employment history"
Consists of <b>previous employment data</b> inputs - each employment entry is a seperate scene:
<ul>
	<li>List view - shows all created <b>employment history</b> entries - placed on the left of the scene. List entries will be identified by company name (in bold) and position taken (in smaller font, below company name)</li>
	<li>Name of the company - input text box</li>
	<li>Address of the company - input text box</li>
	<li>NIP number (optional) - input text box</li>
	<li>Position within the company - input text box</li>
	<li>Start date - input date </li>
	<li>End date (optional) - input text box - defaults to string "Today"</li>
	<li><b>+</b> button - to add another <b>employment history</b> entry</li>
	<li><b>Next</b> button - only works when all data is correct and moves user to the next scene</li>
	<li><b>Back arrow</b> - takes user to the previous scene</li>
</ul>