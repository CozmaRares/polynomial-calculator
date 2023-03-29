# Assignment 1 - Polynomial Calculator

## Contents

1. [Assignment Objective](#assignment-objective)
2. [Problem Analysis](#problem-analysis)
3. [Design](#design)
4. [Implementation](#implementation)
5. [Results](#results)
6. [Future Developments](#future-developments)
7. [Biography](#biography)

## Assignment Objective

Design and implement a polynomial calculator with a dedicated graphical interface through which the
user can insert polynomials, select the mathematical operation to be performed and view the result.

## Problem Analysis

### Functional requirements

* calculator should allow user to input two polynomial equations
* calculator should allow user to select one of the following mathematical operations:
  * addition
  * subtraction
  * multiplication
  * division
  * integration
  * differentiation
* if an error occurs during the calculation display its message

### Use Cases

1. __Use case:__ _addition_ \
   __Primary actor:__ _user_ \
   __Success scenario:__

    * user inserts the polynomial equations into the text fields
    * user clicks the "+" button
    * the calculator performs the addition
    * a popup window shows the result

   __Error scenario:__ a popup window shows the error message
    <br />

2. __Use case:__ _subtraction_ \
   __Primary actor:__ _user_ \
   __Success scenario:__
    * user inserts the polynomial equations into the text fields
    * user clicks the "-" button
    * the calculator performs the subtraction
    * a popup window shows the result

   __Error scenario:__ a popup window shows the error message
    <br />

3. __Use case:__ _multiplication_ \
   __Primary actor:__ _user_ \
   __Success scenario:__
    * user inserts the polynomial equations into the text fields
    * user clicks the "*" button
    * the calculator performs the multiplication
    * a popup window shows the result

   __Error scenario:__ a popup window shows the error message
    <br />

4. __Use case:__ _division_ \
   __Primary actor:__ _user_ \
   __Success scenario:__
    * user inserts the polynomial equations into the text fields
    * user clicks the "/" button
    * the calculator performs the division
    * a popup window shows the result

   __Error scenario:__ a popup window shows the error message
    <br />

5. __Use case:__ _integration_ \
   __Primary actor:__ _user_ \
   __Success scenario:__
    * user inserts the polynomial equations into the text fields
    * user clicks the "integrate" button
    * the calculator performs the integration
    * a popup window shows the result

   __Error scenario:__ a popup window shows the error message
    <br />

6. __Use case:__ _differentiation_ \
   __Primary actor:__ _user_ \
   __Success scenario:__
    * user inserts the polynomial equations into the text fields
    * user clicks the "differentiate" button
    * the calculator performs the differentiation
    * a popup window shows the result

   __Error scenario:__ a popup window shows the error message
    <br />

## Design

### OOP Design

The main classes that I have identified are:

* __Polynomial__: contains the relevant information about the polynomial, i.e. powers of X and their corresponding coefficients
* __Operation__: there should be one class for each operation that is supported
* __GUI__: 🤮

### Used Data Structures

* __HashMap__ - used in the Polynomial class to store the relevant data
* __Set__ Iterator - used to get the key set of the HashMap, i.e. the powers of X from a polynomial
* __ArrayList__ - used in the toString method of the Polynomial class to print its monomials, sorted descending by power

## Implementation

### Structure of the Project

<pre>
calculator
├── controller
|   └── Controller
├── model
|   ├── Polynomial
|   └── operations
|       ├── Addition
|       ├── Subtraction
|       ├── Multiplication
|       ├── Division
|       ├── Integration
|       └── Differentiation
├── view
|   ├── View
|   └── Popup
└── utils
    └── Decimal
</pre>

### Description of Classes

1. Controller
    * __Fields:__
        * _View_ view
    * __Methods:__
        * wrapInActionListener
    * __Description:__
        * controls the visibility of the view and also sets its the action listeners
        * __wrapInActionListener__ accepts as a parameter a consumer function of two polynomials that performs one of the mathematical operations implemented and returns an ActionListener that calls this consumer.

2. Polynomial
    * __Fields:__
        * _Map<Integer,Decimal>_ monomials
        * _int_ degree
        * a bunch of static fields used for configuration
    * __Methods:__
        * fromString
    * __Description:__
        * contains the useful information about a polynomial equation
        * _fromString_ accepts a string as a parameter and returns a Polynomial object after parsing the string using the regex rules defined in the class

3. Addition
    * __Methods:__
        * apply
    * __Description:__
        * performs the addition of two polynomial equations

4. Subtraction
    * __Methods:__
        * apply
    * __Description:__
        * performs the subtraction of two polynomial equations

5. Multiplication
    * __Methods:__
        * apply
    * __Description:__
        * performs the multiplication of two polynomial equations

6. Division
    * __Methods:__
        * apply
    * __Description:__
        * performs the division of two polynomial equations

7. Integration
    * __Methods:__
        * apply
    * __Description:__
        * performs the integration of a polynomial equation

8. Differentiation
    * __Methods:__
        * apply
    * __Description:__
        * performs the differentiation of a polynomial equation

9. View
    * __Fields:__
        * 6 buttons for each mathematical operation
        * 2 text fields for each equation
    * __Methods:__
        * getters for the equations
        * setters for the action listeners of the buttons
    * __Description:__
        * The main GUI of the application.

10. Popup
    * __Description:__
        * secondary GUI class of the application
        * used for displaying the results and error messages

11. Decimal
    * __Description:__
        * wrapper for the BigDecimal class

## Results

### What is tested?

For the __Polynomial__ class, I have only tested the _fromString_ method
with different strings that represent both valid and invalid polynomials. \
For the classes that represent _operations_, I have tested some working cases and edge cases.

### Result of Tests

```bash
mvn test
```

<pre>
[<span style="color: skyblue">INFO</span>] -------------------------------------------------------
[<span style="color: skyblue">INFO</span>]  T E S T S
[<span style="color: skyblue">INFO</span>] -------------------------------------------------------
[<span style="color: skyblue">INFO</span>] Running calculator.AdditionTest
[<span style="color: skyblue">INFO</span>]  <span style="color: lightgreen">Tests run: 3</span>, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.09 s - in calculator.AdditionTest
[<span style="color: skyblue">INFO</span>] Running calculator.DifferentiationTest
[<span style="color: skyblue">INFO</span>]  <span style="color: lightgreen">Tests run: 4</span>, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in calculator.DifferentiationTest
[<span style="color: skyblue">INFO</span>] Running calculator.DivisionTest
[<span style="color: skyblue">INFO</span>]  <span style="color: lightgreen">Tests run: 5</span>, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.006 s - in calculator.DivisionTest
[<span style="color: skyblue">INFO</span>] Running calculator.IntegrationTest
[<span style="color: skyblue">INFO</span>]  <span style="color: lightgreen">Tests run: 4</span>, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 s - in calculator.IntegrationTest
[<span style="color: skyblue">INFO</span>] Running calculator.MultiplicationTest
[<span style="color: skyblue">INFO</span>]  <span style="color: lightgreen">Tests run: 3</span>, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 s - in calculator.MultiplicationTest
[<span style="color: skyblue">INFO</span>] Running calculator.PolynomialTest
[<span style="color: skyblue">INFO</span>]  <span style="color: lightgreen">Tests run: 19</span>, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.008 s - in calculator.PolynomialTest
[<span style="color: skyblue">INFO</span>] Running calculator.SubtractionTest
[<span style="color: skyblue">INFO</span>]  <span style="color: lightgreen">Tests run: 3</span>, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in calculator.SubtractionTest
[<span style="color: skyblue">INFO</span>]
[<span style="color: skyblue">INFO</span>] Results:
[<span style="color: skyblue">INFO</span>]
[<span style="color: skyblue">INFO</span>]  <span style="color: lightgreen">Tests run: 41, Failures: 0, Errors: 0, Skipped: 0</span>
[<span style="color: skyblue">INFO</span>]
[<span style="color: skyblue">INFO</span>] ------------------------------------------------------------------------
</pre>

## Future Developments

* accept any letter for the variable, not just x
* add support for negative and real powers
* better GUI
* rewrite the app in Rust

## Biography

* [the Mecca of programmers](https://stackoverflow.com/)
