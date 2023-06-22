![Screenshot 2023-06-18 at 9.04.30 PM.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F2g%2F83tzbxnn5nn5njbh694yv7dh0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_ysReLA%2FScreenshot%202023-06-18%20at%209.04.30%20PM.png)

[pa05-template-1.0-SNAPSHOT.jar](build%2Flibs%2Fpa05-template-1.0-SNAPSHOT.jar)

## Pitch:

 The Bullet Journal App is a new and easy-to-use app to plan out your week to perfection!
With a welcoming display and user-friendly handling, this app is quick to learn and suitable for everyone.
Whether it's planning for final week or spacing out your chores for Sunday, the Bullet Journal has easy task and event
creation with a satisfying checklist to see those pesky tasks get checked off as you complete your chores!
Nowadays notes apps can be overwhelming-- between tons of templates and loads of confusing features, the Bullet Journal
is a straight forward organization tool for any user to stay on top of their busy schedules with this neat, aesthetic,
and easy to use app!

## Screenshot of GUI:

![Screenshot 2023-06-22 at 12.54.55 AM.png](..%2F..%2FScreenshot%202023-06-22%20at%2012.54.55%20AM.png)

## SOLID Principles:

# Single Responsibility Principle:

   By adhering to the **Single Responsibility Principle**, the Controller classes maintain a clear and focused responsibility, 
enhancing code organization and maintainability. Each Controller is dedicated to managing scene transitions and 
coordinating the flow of control, ensuring that each class has a well-defined purpose. This design approach promotes 
easier comprehension and modification of individual Controller classes. It also facilitates code reuse and 
extensibility, as each Controller can be easily integrated into different parts of the application without 
impacting unrelated functionalities.

# Open/Closed Principle:

  The **Open/Closed Principle** can be seen in the Widget Abstract class because the Task and Event extend the Widget class,
but the Widget Abstract class is never modified. The Widget Abstract class remains closed for modification, meaning its
core implementation remains unchanged. However, it remains open for an extension, enabling the creation of new Widget 
subclasses to introduce additional features or specialized behaviors. Our implementation allows for the system to 
evolve and accommodate new requirements without directly modifying the Widget Abstract class. It encourages the 
development of flexible and adaptable code, where new functionality can be introduced through inheritance and 
polymorphism, while the existing code remains unaffected.

# Liskov's Principle:

  **Liskov's Principle** is shown when a one of our Controller classes has an "Is-a" relationship with the Controller
interface. The Controller interface serves as a contract that defines a set of behaviors and responsibilities expected 
from any class implementing it. The Controller classes, which extend or implement the Controller interface, can be used 
interchangeably without affecting the correctness of the program. We ensure that the derived classes conform to the 
contract defined by the interface, providing consistent behavior throughout the code.
Inheritance is displayed through the GraphicalView class that was inheritance from the View interface the load method. 
The GraphicalView class is considered a type of View. This relationship allows instances of the GraphicalView class
to be treated as instances of the View class, providing flexibility and compatibility in our code.

# Dependency Inversion Principle:

  **Dependency Inversion Principle** can be seen by the passing of the Week to each controller when it is instantiated.
The Week object is then used in each controller to mutate different aspects of the Week based on the user's actions. 
The Week object serves as a stable and consistent interface, providing a contract for accessing and manipulating the 
properties and behaviors related to a week. The controllers, irrespective of their specific functionalities, rely on 
this shared abstraction, enabling them to perform operations on the Week object without being tightly coupled to its 
implementation details.


## Extensibility:

Custom Themes: Our implementation could be extensible for this feature because we have fields of each week day in the
Week View controller. Having the Vboxes for each day's tasks/events allows for open and easy modification of the color 
for the Vbox background, and font that will be displayed on the Vboxes. This could be done by prompting the user to 
choose from different color/font options that are button choices, and then using the data to set the background color, 
font type, and color scheme for the application. Then this preference will have to be reflected in our JSON records to 
store the data of the custom theme. This extension could be accomplished by first adding a new Vbox on the welcome page 
that says "customize theme". Then we would switch the scene with a new CustomizeThemeController, and have a scene of 
buttons that has color choices and font choices for the user to press. After the four different options are selected, 
we would pull this data, set the background, vboxes, and font for the application. Then store this information in the 
JSON records as a ColorScheme record that has fields of color and font styles.

## Attribution:

The Splash Screen was created through the use of Canva. The image was created by Annie Meaney.