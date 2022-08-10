# InViewPrep

Trainign app attempting to utilize libraries from main for learning

## Home Screen

Recycler View setup using Mosby3 and Conductor to navigate to smaller projects within the app. Uses MVI/MVP architecture.

Note: Attempts to use PublishSubject to navigate with groupie's onItemClickListener are not working. Using navigation directly in groupie's Item for now.

## Counter

Simple counter screen using controller. 

Note: Convert from ViewModel to MVP, currently functionality is not working while switching

## Greetings

Simple greetings screens to send 1 greeting to next screen on button click. Start of Mosby Integration.

## Messenger

Small chat messenger between 2 screens. On ImageButton click sends the message to the new screen. Empty messages are given an error warning. Uses groupie to display chat messages stored in a local repository. Messages are assigned a user and have different displays on screen based on who the current screen's user is. Messages have a timestamp and when viewed by the other user, include a profile picture.

Note: Currently uses a singleton repository, changing soon
