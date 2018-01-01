# trafficizer

Trafficizer is an attempt to build a web-based traffic (in and out) generation system for my layout. The layout is a smallish switching layout, 


### Main Features
* Create Read Update Delete (CRUD operations) on classes (sizes) of operating sessions 
* Ability to generate different sizes of operating sessions based on user whim (eg: small/medium/large), because different days will ahve different loads.
* Generate a delivery form for engineers and conductors, maybe even make it resizable for tablets, that is viewable later
* Remember what has been done from one session to the next, so I can get loads in and out.
* Treat loads out differently than loads in (ie: they are discovered by conductor at sidings, rather than on a pickup form).
* CRUD operations on session, so that I can add/remove pickups/dropoffs
* Each session has a sequence # and/or date, so that I can 'travel in time' and redo sessions
* CRUD operations on car state/existence/position, so that I can add/remove cars, clean up boo boos and unfinished sessions
* Track bad orders on cars, to be able to understand car maintenance
* Modify traffic generation criteria for sidings. For the time being, the physical existence of sidings will not change, so there is no real need to have the sidings changeable.

### Algorithm
Inbound
* Iterate through all sidings in a random order
* Each siding that has room for new cars, has a percentage chance that they will need traffic today.
* Multiply that percentage by the size percentage, to get a probability of a car being needed.
* Generate a random number, if it's greater than the probability, carry on!
* Select an appropriate car, and add it to the pickup request

Outbound
* Iterate through all cars on layout
* Has it been there long enough to be unloaded/loaded?
* Multiply that percentage by the size percentage, to get a probability of a car being picked up today.
* Generate a random number, if it's greater than the probability, carry on!
* Select an appropriate car, and add it to the pickup request
