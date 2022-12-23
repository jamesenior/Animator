# Group_OOD

The Easy Animator Application  

PART 1: Model (Part 2 below)

This is the ReadMe file for our EasyAnimatorModel.

First, we created our own classes for a Color inputting RGB, and a Posn with an x and y position.  These classes are pretty self explanatory. 

We represent moves through the Mutation class.  The Mutation class is abstract which is then extended by either a ReSize, 
a Translation (Move), or ColorShift.  A Mutation has a start tick, an end tick, and a Type.  The type is represented by our enum called MutationType,
which has types ReSize, Translation or ColorShift.

Our ShapeState interface and the ShapeStateImpl class represent the state of a shape at a single tick.  Here we enforce the conditions for a Shape,
such as not having a negative width or height.   

For shapes, we used the Shape interface. This is implemented by the Abstract Shape class, which defines behaviors for any shape added to the cs3500.animator.model.
We defined Rectangle and Oval classes that extend this as well for examples of shapes.
The only thing the Rectangle and Oval classes really add is a method that gets the type of shape as a String, as they share
most of their functionality for now.
An abtract shape has three fields: 
one is its name, one is a NavigableMap of Integers and ShapeStates, and one is a list of mutations.  
 - the name of the shape does not change so it is final
 - when a Shape is created with width, height, a posn, and a color, a ShapeState is created with those and put in the Map at tick 0.
 - The conditions for adding a mutation to the cs3500.animator.model and removing are set in AbstractShape.  We prevent overlapping motions here as well as accounting for gaps.
 - IMPORTANT: Our cs3500.animator.model interprets gaps in between animations as a transitional, so the shapes simply do not move in between motions.

We made an interface for the Model called AnimatorModel.  This interface was the implemented by our SimpleAnimatorModel class. The SimpleAnimatorModel
contains a list of Shapes that contains the shapes being animated.  From the cs3500.animator.model, you can add shapes as well as mutations/moves.  We also overrided 
toString to create the text representation of the Model.


PART 2: View

For part 2, we implemented the Views for the Model.

Firstly, we needed to make changes to the Model.
We made the model itself take in a canvas size,
so we added fields for canvas width and height.
Additionally, we added multiple methods to Shape to help build the SVG String.
More on that found below.

We first created a AnimatorView Interface to represent a view.
This interface only contains the render method,
which renders the certain type of view and takes in a tickRate of ticks per second.

We then implement this interface in the AbsAnimatorView class.
Here in the super constructor we enforce that the model can't be null.

1. Text Animator View  

In our text Animator view, we simply created an output String similar to the one
from the last assignment, except this time, we represented time intervals as seconds.
We did so by multiplying the tick by the tickRate.  To build the string we
iterated through each shape and then iterated through each state of
that shape. (All Found in render method.)  
  
2. JSwing Animator View  

For our visual view, we created the SwingAnimatorView class.
This class has the JFrame as a field.
The constructor for the view renders it at the given tickRate.
We created an AnimationPanel class and overrode PaintComponent to paint each shape at its current tick.
We made a timer that repeatedly executes a Refresh object, which in turn repaints the AnimationPanel.
Each time the panel is repainted, the current tick increases.

3. SVG Animator View

In the SVG Animator view, we iterated through each shape
in the given model and built a String for it.  To build the SVG String for a shape, we defined
the method getSVGTag in the AbsShape class. This method uses multiple abstract helper methods such as
getSVGDims, getSVGPosns and getSVGName that are then defined
in the Rectangle and Oval Classes.  These helper methods account for the
differences in the SVG outputs for the different types of shapes.

4. Main class

Parses through inputs to see if they begin with a command -in, -view, -out, or -speed.
Each command can only be entered once.
Uses JOptionPanes to show error messages if commands are entered incorrectly.

PART 3: Interactive Animator

CHANGES TO PREVIOUS VERSION:
Split the model into read-only and full functionality interfaces to prevent public access to methods that can mutate the model.

INTERACTIVE VIEW:
The new interactive view extends our previous SwingAnimatorView class.
It makes the timer and its associated ActionListener fields of the class.
ActionListener is a LoopRefresh (extension of Refresh ActionListener), which supports looping.
Uses a new JPanel class called ControlPanel to visualize the available interactions, and which ones are active at any time.

We also created a controller interface that starts animations and handles key inputs if necessary.
Uses an AnimatorKeyAdapter (extension of KeyAdapter) to pass key inputs to the controller implementation.

ALGORITHM ANIMATIONS:
The two animations we created for this assignment are for two types of sorting:  
Bubble Sort and Selection Sort.  In each animation, a set of rectangles are sorted based on the type of sorting algorithm.

To create the two animations required for this assignment, we created the AlgoAnimation interface.
This interface is then implemented by the Abstract class AlgoAnimation.
This interface has three methods: sort, moveString and getFinalString.
* sort does the main work of the code, by iterating through a given array of integers, sorting them
and then through each iteration, storing any moves made.
* moveString creates the concatenated String for a single move for an animation.
* getFinalString returns the final output String for an AlgoAnimation.  

moveString and getFinalString are defined in the abstract class, as well as overriding toString  
to create and store the final output String for the animation.  
Sort is then defined in the classes BubbleSortAlgoAnimation and SelectionSortAlgoAnimation classes,  
as the conditions for sorting are set there.   
The main method for writing the animations to a file can be found in the MainAnim class. 
the main method takes in: 
* a type (for the sort type) selection or bubble
* an array representing the input array of integers (heights of the rectangles)
* an output file to write the String to.
* EXAMPLE INPUTS: -array 5 1 3 2 4 -type bubble -out bubsort.txt
The main method handles the inputs, then creates an animation based on the inputs, and writes its string to a file.

The animation text files (for both bubble and selection sort) can be found in the 'resources' directory of our project.





