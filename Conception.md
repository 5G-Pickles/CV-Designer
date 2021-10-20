## First window - "Hello"
Consists of the logo of the app and a <b>Start</b> button - in the futuer might also consist of the <b>Login</b> button and <b>Register</b> button.

## Second window - "Basic data"
Consists of <b>basic data</b> inputs:
<ul>
	<li>Name input text box - with not-needed UTF-8 characters removal (only alphabet accepted)</li>
	<li>Surname input text box - with not-needed UTF-8 characters removal (only alphabet accepted)</li>
	<li>Telephone number input text box - checks if the telephone number is in Polish format (9 digits), accepts the number in space or dash independet forms, only numbers</li>
	<li>E-mail address input text box - checks if e-mail address is correct (format <i>address@domain.com</i>)</li>
	<ul>
		<li>check box "Send confirmation e-mail" - when checked an e-mail will be sent to the provided e-mail address (optional 6 digit code verification)</li>
	</ul>
	<li><b>Next</b> button - only works when all data is correct and moves user to the next window</li>
</ul>

## Third window - "Basic data"
Consits of <b>basic data</b> inputs - all of the below data is parsed and showed within MapView upon clicking the <b>Confirm</b> button:
<ul>
	<li>Country - defaults to Poland - at first only option, in the future might be changed to support more countries</li>
	<li>City - at first only Polish cities supported, in future more countries might get support</li>
	<li>Road - only alphabet</li>
	<li>Building number - only alphanumeric</li>
	<li>Apartment number - only numeric</li>
	<li>ZIP code - first only Polish ZIP code formats supported in XXXXX or XX-XXX or XX XXX format</li>
	<li>MapView - half of the stage is taken for this component, uses GoogleMaps API to display the given and parsed address data upon clicking the <b>Confirm</b> button </li>
	<li><b>Next</b> button - only works when all data is correct and moves user to the next window</li>
</ul>