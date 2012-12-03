

Easy Diagnosis: README
COSC 310 Software Engineering

1.	Installation
1.1.	Hardware and Software requirements
1.2.	Installation instructions
2.	Execution
2.1.	Hardware and Software requirements
2.2.	Execution instructions
3.	Overview
3.1.	System summary
3.2.	Feature list
3.3.	Test cases
4.	Files and Directories
4.1.	Directory and subdirectories
4.2.	Source and dependent file list
5.	Bugs and Issues
5.1.	Known bugs and triggers
5.2.	Issues considered for future improvement











1.	Installation:
1.1.	Hardware and Software requirements
•	Windows Operating System (XP or higher)
1.2.	Installation instructions
•	Download and install the jre7 from http://www.oracle.com/technetwork/java/javase/downloads/jre7u9-downloads-1859586.html 
•	Download and install winRar from
http://www.rarlab.com/download.htm
•	Download the latest production release of MongoDB from
http://www.mongodb.org/downloads
o	Extract the.zip file contents to “C:\”
•	In “C:\” create a folder labeled “data” and another folder within it labeled “db”
•	Right-click on “EasyDiagnosis.rar” and select “Extract files…”
o	Choose a destination and select “OK”
2.	Execution:
2.1.	Hardware and Software requirements
•	Windows Operating System (XP or higher)
•	Java Run-time Environment 7
2.2.	Execution instructions
•	Find and double-click the file labeled “EasyDiagnosis.exe” in the previously chosen location
3.	Overview
3.1.	System summary
The Easy Diagnosis system is an interactive chat agent program design for use by a psychiatrist and patient in order to detect symptoms from the patient and help guide the psychiatrist to a diagnosis by providing symptoms, disorders, and conversation guidance.
3.2.	Feature list
Interactive GUI: The program uses a GUI for easy interaction between the user and the system. This includes a text field for user input, a text area for displaying conversation history, and a tabbed panel for additional features.
•	The text input field allows the user to enter a sentence (or multiple sentences) and then submit the entry by either pressing the “Enter” key, or clicking “Send”.
•	The text area displays the entire conversation, prefixing each sentence with the identity of who sent the message (either the user or the system). When a conversation reaches a length exceeding the size of the window, a scroll bar is activated to allow easy browsing. When a conversation ends, the symptom scores and diagnosis scores for the patient are also displayed here along with a message from the system stating its confidence in the diagnosis.
•	In the tabbed panel, 4 additional features are available including “Symptoms”, “Diagnosis”, “Conversation Tree”, and “Statistics”.
o	The symptoms tab lists all the symptoms that the user (patient) has exhibited throughout the conversation and is updated with each input from the user.
o	The diagnosis tab provides the current most plausible diagnosis and is updated with each input from the user (keeping the old diagnosis to see how it has changed through the conversation).
o	The conversation tree tab provides an extensive listing of all possible responses as well as the chosen response by the system; after each input from the user, the system decides on its next response and displays it along with what responses could be used next.
o	Finally, the statistics tab provides varying statistical data (including but not limited to: total symptom scores shown across all patients and total diagnosis scores across all patients) for analytical purposes.
Database: In order to collect information on a ranging history of patients, the system stores selected data from each completed conversation to a MongoDB for future use. Within the database is a single collection (table) containing one document (tuple) per conversation. This document stores the patients name, symptom scores, diagnosis scores, trigger words used, and the date/time of the conversation. 
3.3.	Test cases
In order to display the systems capabilities of diagnosing a wide range of patients exhibiting a multitude of symptom combinations, each disorder the system is able to diagnose was researched to compile enough grounds for interacting with the system acting as a patient with that particular disorder. Here a passing test is considered one which received the correct diagnosis with a confidence level %50 or higher.
•	Test 1: Manic Disorder
•	Test 2: Intermittent Explosive Disorder (IED)
•	Test 3: Depression
•	Test 4: Bipolar
•	Test 5: Anxiety Disorder
•	Test 6: Amnesia
•	Test 7: Alzheimer’s
•	Test 8: Schizophrenia

4.	Files and directories
4.1.	Directory and subdirectories
4.2.	Source and dependant file list
5.	Bugs and Issues
5.1.	Known bugs and triggers
•	If the application window is moved and then the “Conversation Tree” tab is navigated to or away from, the window will center itself on the screen.
•	
5.2.	Issues considered for future improvement
Although the system is functional and proven to be accurate in performing its purpose, areas still exist where improvements could be made. The conversational aspect of the system parallels that of a questionnaire, but to feel more fluent and human some comment-type responses or statements should be built into the system. Another area which could be improved is the statistics tab of the interface. The statistics provided are quite basic and to provide a more detailed understand of the history of patients, symptoms, and diagnoses, more correlations need to be made. A final area that could be improved upon is the GUI itself; the interface has a raw feel to it and could be better styled to feel more inviting and interesting.
