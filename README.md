Android Applications.

Under development.

Introduction
Presently developing an app that allows seamless connectivity in Real-time between security department of the company and its employees. Uses FireBase Cloud by Google for storing the image captured and data in the realtime NOSQL database.
When a guest comes to visit an employee, instead of placing calls to and fro. The security guard captures a photo of the guest, and takes their name. Then he places a request for authentication by concerned employee. 

Application Suite is divided into 2 parts:

Camera named package: 
This is the Requester app. The security guard take a picture of the guests. Take input data such as name and phone number, concerned employee to be met.
Once this is done, a request-entry is made in RealTime database of FireBase(Google Cloud). Which then moves the flow to the next intent which checks whether this request is acknowledged or rejected by the concerned employee.

MyLogin named package:
This is the acknowledger app. The employee logins and registers his device, and then checks for any pending requests in the database. If there is he is provided with an option Accept or not. Which is then updated in the corresponding entry in the database, and removes this from request TAB

Tools:
•	Android SDK (API 28)
•	FireBase SimpleDB
•	Working internet connectivity

Flow description:

Step 1.	User is prompted to choose between:
a.	Requester: Go to step 2
b.	Acknowledger(employee): Go to step 5

Step 2.	Collect guest information
a.	Name
b.	Photo
c.	Purpose for visiting

Step 3.	Send the request for the concerned employee
If accepted: Show green screen
Else: Display busy

Step 4.	Go to step 7

Step 5.	Employee must login and register to accept request.

Step 6.	When a request comes, the employee is shown the photo of the guest, their name followed by an option to accept/reject the same with optional message.

Step 7.	Close App

