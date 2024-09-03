=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections - Collections must be used to render all the barriers, as they
  are continuously generated. This is suitable for a member of the collection
  framework as the size is not known before creating it. I decided to use an
  ArrayList as the order of the data stored is irrelevant, and I do not require
  keep value matches. The use concerns the objects and the amount of objects mainly.

  2. Inheritance and Subtyping - There are two key elements of the game. The barriers
  and the sprite. Though these are both game objects, they act very differently, so
  I did not use a game object super class, but there are multiple different variations
  of barrier and sprite, so I used a super class for each individual class to inherit
  commonalities like being draw.

  3. 2D Array -
  This implements a difficulty change. The barriers have two main values which are
  the speed of rotation and the shrink. Since these values should be paired in some
  way I decided to use a 2D and map these values to change the barriers. The array is
  suitable here as the only thing that barriers have to deal with is primitive values
  and the data does not need to be indexed in a specific way. The length is known as well.

  4. Testable Component - This implements most of the parts within the game (all none GUI parts).
  As much of the code is based on areas, using tests to judge coordinates for any moving part
  (Sprite and barriers) is very useful.

  5. Barrier Collision Detection (Extra) - This was required for my game as it is the crux of the
  state. When the sprite collides with a barrier the games state will change,
  so will many other values.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  SpriteForm - Deals with create different sprite objects (Things that the user avoids)

  Sprite, SpriteMedium, SpriteHard - Deals with different variations of the sprite,
  make controlling it more difficult. These are all subtypes of the SpriteForm.

  ShapeBarrier - Deals with creating different shaped barriers that act differently.

  Square, Hexagon, Septagon - Deals with different variations of the barriers,
  allows the shape ot change during runtime. These are all subtypes of the ShapeBarrier.

  RunSckware - Builds interface for interacting with the game

  Game - Entry point

  Levels - A series of 2D arrays which are used to map properties to barriers

  Leaderboard - Holds users scores from the *current* session

  EndScreen - Allows user to set a score, and show them what score they received
  at the end of each round.

  GameCourt - primary game logic for how different objects interact
  with one another. Take time to understand how the timer interacts with the
  different methods and how it repaints the GUI on every tick().


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  Collision detection proved to be very difficult, especially to get any accuracy with
  various different shapes. The GUI has also proved to be difficult to dynamically
  update and refresh values to be shown to the user.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
   No, this solution has high coupling and low cohesion due to the collision
   detection implementation.

   Private state is encapsulated relatively well, most fields are private where they
   can be. If I was to refactor this, instead of using abstract superclasses, I would
   use more interfaces.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

  Font from Dafont.com
