# Premium Auto Rental

## *author: Konstantin Svirski*

## How it's works

The application is designed to automate car rental. An authorized user orders a car and pays for it. 
The administrator confirms the reservation or refuses, indicating the reason for the refusal. At the end of the lease, 
the user returns the car back and can leave a comment on the car. The administrator confirms the delivery of the car. 
If there are problems with the car, then he writes out a fine that the user must pay.

## Actors

* Guest (not authorized user)
* User (authorized user)
* Admin (user with role of administrator)

### Guest functionality:

* Signing in
* Signing up
* View cars and make selection of them in different ways
* View detail description of car
* Switch language

### Users functionality:

* All guest's abilities
* Signing out
* View his/her page with personal information 
* Edit personal information
* Rent car
* View orders and inforamtion of them
* Release rent with comment to car
* Pay a fine if with car was problems

### Admin functionality:

* All guest's abilities
* Signing out
* View his/her page with personal information 
* Edit personal information
* View all users
* Block/unblock user
* Appoint administrator
* View information of specific user
* View all users orders
* View orders of specific user
* Apply rent of user
* Deny in rent with indicating the reason
* Approve release of rent car
* Issue a fine for a specific order
* Add car in list
