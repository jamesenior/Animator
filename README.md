The Easy Animator Application

This is the ReadMe file for our EasyAnimatorModel.

First, we created our own classes for a Color inputting RGB, and a Posn with an x and y position.  These classes are pretty self explanatory. 

We represent moves through the Mutation class.  The Mutation class is abstract which is then extended by either a ReSize, 
a Translation (Move), or ColorShift.  A Mutation has a start tick, an end tick, and a Type.  The type is represented by our enum called MutationType,
which has types ReSize, Translation or ColorShift.

Our ShapeState interface and the ShapeStateImpl class represent the state of a shape at a single tick.  Here we enforce the conditions for a Shape,
such as not having a negative width or height.   

For shapes, we used the Shape interface. This is implemented by the Abstract Shape class, which defines behaviors for any shape added to the model.
We defined Rectangle and Oval classes that extend this as well for examples of shapes.
The only thing the Rectangle and Oval classes really add is a method that gets the type of shape as a String, as they share
most of their functionality for now.
An abtract shape has three fields: 
one is its name, one is a NavigableMap of Integers and ShapeStates, and one is a list of mutations.  
 - the name of the shape does not change so it is final
 - when a Shape is created with width, height, a posn, and a color, a ShapeState is created with those and put in the Map at tick 0.
 - The conditions for adding a mutation to the model and removing are set in AbstractShape.  We prevent overlapping motions here as well as accounting for gaps.
 - IMPORTANT: Our model interprets gaps in between animations as a transitional, so the shapes simply do not move in between motions.

We made an interface for the Model called AnimatorModel.  This interface was the implemented by our SimpleAnimatorModel class. The SimpleAnimatorModel
contains a list of Shapes that contains the shapes being animated.  From the model, you can add shapes as well as mutations/moves.  We also overrided 
toString to create the text representation of the Model.
