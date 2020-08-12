# Gravity Simulator



##### A personal project initiated in CPSC210 at the University of British Columbia.

___

###### Author: Connor Quigg 
###### Degree: Combined Honours in Physics and Computer Science
###### Created: July 23, 2020

---

The ***purpose*** of this project is to simulate simple gravitational interactions in a manner that is both visually and 
cognitively stimulating.  My hopes are that this project will serve as a catalyst for interest in orbital dynamics and 
the cosmos within the user. The initial implementation will allow a user to create and modify orbital systems, observe 
system evolution over time, and save their creations for later use. 

The ***inspiration*** behind this project materializes out of my own love for physics and the universe.  I present this
program for both the layperson and physics enthusiast alike.  I intend the user experience to be simple, rewarding, and 
engaging, while accommodating those who wish to monitor more advanced parameters.

---

### User stories

A third iteration:
- As a user, I want to be able to observe solar system evolution over time.
- As a user, I want to be able to add a planet to a list of planets that comprise a solar system
- As a user, I want to be able to load solar systems already in the program.
- As a user, I want to be able to define initial orbital parameters for a custom planet upon creation.
- As a user, I want to be able to view a list of current planets in the solar system.
- As a user, I want to be given the option to save a solar system to a file when I quit the program
- As a user, I want to be able to reload a solar system in the exact state I left it.
- As a user, I want the option to delete a planet from a my current solar system

---

### Instructions for Grader

First things first, run ***Main***.

- You can generate the first required event by clicking the "new" button. A window will open prompting you to input
parameters for a new planet to add to the solar system (a list of planets).  Please note the bounds for x and y 
positions.  The color field will match a color string like "red," "blue," "green," etc. or just default to magenta. If 
you wish to add another planet, click yes.

- You can generate the second required event by clicking the "inspect" button.  This will display a list of planets in
the current solar system.  

- You can generate an additional event by clicking the "delete a planet" button.  Enter the name of the planet you wish
to remove from the solar system.

- You can locate my visual component by clicking the "evolve" button.  This will open a new window and simulate the 
gravitational interactions between all planets in the solar system.  ***NOTE*** since I used realistic physics, random
planet parameters may result in the planets moving too quickly.  I recommend choosing velocities < 1 so that the
planets don't accelerate and move too quickly off the screen.  ***OR*** click the "load stored" button, then click 
"evolve."  This will simulate a system hard-coded into the program, so you can clearly see them move without having to 
create planets with parameters necessary for an interesting simulation.  

- You can save the state of my application by clicking the "save" button.  A message window will appear if the system 
saved to the file.

- You can reload the state of my application by clicking the "reload" button.

---

### Phase 4: Task 2

My project has a bi-directional association between the GravityApp class, and the MenuPanel class.  GravityApp extends
JFrame, and has a MenuPanel field that is used to add the MenuPanel JPanel to the JFrame.  MenuPanel has a GravityApp
field that the JPanel uses to reset the GravityApp solarSystem, and calls GravityApp methods handleColor and addAPlanet.
