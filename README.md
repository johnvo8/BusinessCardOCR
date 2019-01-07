# Business Card OCR Parser

We’ve created a new smartphone app that enables users to snap a photo of a business card and have the information from the card automatically extracted and added to their contact list. We need you to write the component that parses the results of the optical character recognition (OCR) component in order to extract the name, phone number, and email address from the processed business card image.

# Notes
- Requires Maven and Java JDK
- Uses Stanford CoreNLP – Natural language software (https://stanfordnlp.github.io/CoreNLP/) to help with detecting human names.

# Instructions
1) Click on the green "Clone or download" button and click on Download ZIP
2) Extract the contents of the ZIP file into a folder
3) Go to that folder on a console and type the following:

		mvn clean install
		
4) "BusinessCardParser.jar" will be generated inside a folder called "target"
5) Go to the "target" folder
6) Type the following to run the jar:

		java -jar BusinessCardParser.jar <text file here>
	
	Example:
		
		java -jar BusinessCardParser.jar bcp.txt
7) Sample text files can be found in the "test" folder
