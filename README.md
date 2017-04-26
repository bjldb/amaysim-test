#PROJECT CONTENT

##A. Description

This project was created and submitted as a solution for the Amaysim Shopping Cart Exercise:

Amaysim is rebuilding our shopping cart.
In this new version we want to allow our customers to purchase multiple SIM cards simultaneously. 

###1. Catalogue

  i. ult_small
    - Name: Unlimited 1GB
    - Price: $24.90
  ii. ult_medium
    - Name: Unlimited 2GB
    - Price: $29.90
  iv. ult_large
    - Name: Unlimited 5GB
    - Price: $44.90
  iv. 1gb
    - Name: 1 GB Data-pack
    - Price: $9.90

###2. Special Offers and Promotions

  i. A 3 for 2 deal on Unlimited 1GB Sims. So for example, if you buy 3 Unlimited 1GB Sims, you will pay the price of 2 only for the first month.
  ii. The Unlimited 5GB Sim will have a bulk discount applied; whereby the price will drop to $39.90 each for the first month, if the customer buys more than 3.
  iii. We will bundle in a free 1 GB Data-pack free-of-charge with every Unlimited 2GB sold.
  iv. Adding the promo code 'I<3AMAYSIM' will apply a 10% discount across the board.

##B. Implementation

This initial implementation sticks to the specified interface and may lack other functionality such as item removal, quantity indication, etc.

###1. Specification:

* cart = ShoppingCart.new(pricingRules)
* cart.add(item1)
* cart.add(item2, promo_code)
* cart.total
* cart.items

###2. Implementation:

* public class ShoppingCart
  - public ShoppingCart(PricingRules pricingRules) {...}
  - public void add(CatalogueProduct item) {...}
  - public void add(CatalogueProduct item, String promoCode) {...}
  - public Double total() {...}
  - public Map<CatalogueProduct,Integer> items() {...}

###3. Pricing Rules:

- Pricing Rules are used to compute total price and modify cart based on bundling rules. 
- Pricing Rule instances are created independently and may be added to or excluded from the rule set independently as well.

- Pricing Rules are categorized as:

  a. Bundling Rule - rules modifying cart based on bundling-related offers and promos
  b. Computation Rule - rules modifying computation of prices based on offers and promos
  c. Finalization Rules - rules modifying final cart total based on offers and promos 

##C. Limitations

1. As stated above, initial implementation sticks to the specified interface.
2. This project is created to be imported into a bigger project incorporating the shopping cart; thus, this is NOT a runnable project. Nevertheless, tests have been created by scenarios and may be run.
3. Due to time constraints, tests used for TDD are mostly coming from the exercise specification.   

#TESTING THIS PROJECT

##A. Pre-requisites

###1. Java (JDK 7 or higher, JDK 8 preferred) 
  - Installation instructions found [here](http://docs.oracle.com/javase/7/docs/webnotes/install/windows/jdk-installation-windows.html).
###2. Maven 
  - Installation instructions found [here](https://maven.apache.org/install.html).
###3. Git 
  - Installation instructions found [here](https://www.atlassian.com/git/tutorials/install-git).
	
NOTE: In order to use command line for this project, make sure that environment variables Path, JAVA_HOME, M2_HOME, etc are configured correctly

##B. Running the tests (Command Line or Bash (e.g. GitBash))

###1. Clone this project
	- $ git clone https://github.com/bjldb/amaysim-test.git
###2. Run Maven Test
	- $ mvn clean test

#ISSUES REGARDING THIS PROJECT

- Kindly contact me via bjl.deborja@yahoo.com.