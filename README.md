By Vin Leang (vleang@macalester.edu), Isauro Reyes (ireyes@macalester.edu), and Harold Ponce Saldivar (hponcesa@macalester.edu)



<h3 align="center">Vinmo</h3>

<!-- What is Vinmo? -->
## What is Vinmo? Why use Vinmo?
Vinmo is a program that simulates a cash-flow minimizer between a group of people, given a number of expenses among them; both of which are determined by the user. While it may be easy to calculate the lowest amount of payments among 3 people (assuming they each have debts to each other), what about with 5 people? Or 7 people? Or even 10? A cash-flow minimizer, such as Vinmo, makes keeping calculating this amount super quick and efficiently.

<!-- How to use Vinmo -->
## How do I use Vinmo?
As per any other Data Structures project, you will first want to download and open this project onto VSCode, via the GitHub Desktop application. Once you do so, run the code.
Once the program begins running, you should be met with this home screen

![image](<img width="600" alt="Vinmo_Homescreen" src="https://github.com/user-attachments/assets/468d9b6a-b90e-4049-9077-75628c794b79" />
)

The buttons and their functions go as follows:
* "Friends" a separate tab in which the user can add and view their friends.
* "Expenses" a separate tab in which the user can add transactions between friends
* "Quit App" closes the application

## Friends
When clicking on the "Friends" button you should be met with this screen. The "back" button will take you back to the home screen.

![image](<img width="600" alt="Vinmo_Friends1" src="https://github.com/user-attachments/assets/ade8b8d5-7d7a-41df-869e-8a594292456c" />
)

The leftmost text field is the name of the friend you wish to add. The "Add to group" button adds them to a user graph, which will be referenced across the entire program. The text in the middle will display the users currently in the group. Keep in mind, the maximum amount of friends that can be added is 10.

![image](<img width="600" alt="Vinmo_Friends2" src="https://github.com/user-attachments/assets/4cd74a75-b2eb-48bb-829a-4231730d0ad4" />
)

## Expenses
When clicking on the "Expenses" button you should be met with this screen. The "back" button will take you back to the home screen.

![image](<img width="601" alt="Vinmo_Expenses1" src="https://github.com/user-attachments/assets/7ff0fe18-12e1-4c6a-a959-d6d990ba8fa0" />
)

The "Person paying" text field refers to the name of the friend who is the own who paid initially on behalf of another person, meaning that they will be owed money. The "Person owing" text field refers to the name of the friend who was paid on behalf by another person, meaning that they will be owing the other person money. The "Value" text field refers to the amount that is being owed (must be numbers inputted in this text field, letters will not work). The "Add Transaction" button will calculate the lowest amount of transaction performed using the cash flow minimizer algorithm; the results will be displayed below.

![inmage](<img width="600" alt="Vinmo_Expenses2" src="https://github.com/user-attachments/assets/689bc6df-73a9-4a45-bcb3-7248fe7feaad" />
)

<!-- Bugs -->
## Bugs
* The "Description" text field on the "Expenses" tab may not properly display when adding a new transaction.

