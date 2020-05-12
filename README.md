# BrewBook - Shareable Homebrew Recipes

## Problem Statement

One of the most prominent websites for creating homebrew beer recipes is Brewer's Friend (https://www.brewersfriend.com/).
This site is a great resource for for general homebrew information, community interaction, and calculators for formulating
recipes. However, users may only create 5 recipes on the free plan with no ability to delete old, unused recipes. Additionally,
pricing plans are a cost-prohibitive subscription model. To work around this cost, I intend to create a lightweight,
web-based application for creating and sharing an unlimited quantity of beer recipes.

## Tech List
* Security/Authentication
  * Tomcat's JDBC Realm Authentication
  * User role: create brews, view their brews.
  * All: View the landing page, sign up, search for breweries
* Database
  * MySQL
  * Store users and roles
  * Store all data for the brews
* ORM Framework
  * Hibernate 5
* Dependency Management
  * Maven
* Styling 
  * Bootstrap
  * CSS
* Logging
  * Log4J2; including informational logging to help illuminate issues in production.
* Hosting
  * AWS
* Unit Testing
  * JUnit
* IDE
  * IntelliJ IDEA