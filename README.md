# Java programming using SPARQL and Jena

[PRACTICE WORK LINK](http://www-inf.it-sudparis.eu/~gaaloulw/KM/Labs/Lab3/)

## Part I: executing SPARQL queries using a given program

### Write and execute SPARQL queries to response to the following:

-   List the instances of the class Son

```
SELECT ?personnages
WHERE {
	 ?personnages rdf:type ns:Son .
}
```

| personnages |
| ----------- |
| ns:Paul     |
| ns:Pedro    |
| ns:Tom      |
| ns:Thomas   |
| ns:Michael  |

-   List the instances of the class Daughter

```
SELECT ?personnages
WHERE {
	 ?personnages rdf:type ns:Daughter .
}
```

| personnages |
| ----------- |
| ns:Chloé    |
| ns:Claude   |
| ns:Sylvie   |

-   List the instances of the class Parent

```
SELECT ?personnages
WHERE {
	 ?personnages rdf:type ns:Parent .
}
```

| personnages |
| ----------- |
| ns:Marie    |
| ns:Peter    |
| ns:Thomas   |

-   List the instances of the class Father

```
SELECT ?personnages
WHERE {
	 ?personnages rdf:type ns:Father .
}
```

| personnages |
| ----------- |
| ns:Peter    |
| ns:Thomas   |

-   List the instances of the class Mother

```
SELECT ?personnages
WHERE {
	 ?personnages rdf:type ns:Mother .
}
```

| personnages |
| ----------- |
| ns:Marie    |

-   List the instances of the class Grandmother

```
Rule :

[ruleGM: (?gm rdf:type ns:Person) (?gm ns:isMotherOf ?parent) (?parent ns:isParentOf ?pers) -> (?gm rdf:type ns:GrandMother)]


SELECT ?personnages
WHERE {
	 ?personnages rdf:type ns:GrandMother .
}
```

| personnages |
| ----------- |
| ns:Marie    |

-   List the instances of the class Grandfather

```
Rule :

[ruleGF: (?gf rdf:type ns:Person) (?gf ns:isFatherOf ?parent) (?parent ns:isParentOf ?pers) -> (?gf rdf:type ns:GrandFather)]


SELECT ?personnages
WHERE {
	 ?personnages rdf:type ns:GrandFather .
}
```

| personnages |
| ----------- |
| ns:Peter    |

-   List the instances of the class Brother

```
Rule :

[Bro: (?b rdf:type ns:Person) (?b ns:isSonOf ?parent) (?parent ns:isFatherOf ?pers) notEqual(?b, ?pers) -> (?b ns:isBrotherOf ?pers)]


SELECT ?b ?pers
WHERE {
	 ?b ns:isBrotherOf ?pers .
}
```

| b          | pers       |
| ---------- | ---------- |
| ns:Michael | ns:Tom     |
| ns:Thomas  | ns:Paul    |
| ns:Thomas  | ns:Chloé   |
| ns:Thomas  | ns:Sylvie  |
| ns:Paul    | ns:Thomas  |
| ns:Paul    | ns:Chloé   |
| ns:Paul    | ns:Sylvie  |
| ns:Tom     | ns:Michael |

-   List the instances of the class Sister

```
Rule :

[Sis: (?s rdf:type ns:Person) (?b ns:isDaughterOf ?parent) (?parent ns:isMotherOf ?pers) notEqual(?s, ?pers) -> (?s ns:isSisterOf ?pers)]


SELECT ?s ?pers
WHERE {
	 ?s ns:isSisterOf ?pers .
}
```

| s         | pers      |
| --------- | --------- |
| ns:Chloé  | ns:Paul   |
| ns:Chloé  | ns:Thomas |
| ns:Chloé  | ns:Sylvie |
| ns:Sylvie | ns:Paul   |
| ns:Sylvie | ns:Thomas |
| ns:Sylvie | ns:Chloé  |

### Write and execute SPARQL queries (several conditions in WHERE) to response to the following:

-   List (name, age) of children of Peter

```
SELECT ?name ?age
WHERE{
	?name ns:isChildOf ns:Peter .
	?name  ns:age  ?age
}
```

| name      | age           |
| --------- | ------------- |
| ns:Paul   | "38"^^xsd:int |
| ns:Thomas | "40"^^xsd:int |
| ns:Chloé  | "18"^^xsd:int |
| ns:Sylvie | "30"^^xsd:int |

-   List of persons whose father is more that 40 years old

```
SELECT ?name ?father ?age
WHERE{
	?name ns:isChildOf ?father .
	?father rdf:type ns:Father .
	?father  ns:age  ?age .
	FILTER (?age > 40) .
}
```

| name      | father   | age           |
| --------- | -------- | ------------- |
| ns:Paul   | ns:Peter | "70"^^xsd:int |
| ns:Thomas | ns:Peter | "70"^^xsd:int |
| ns:Chloé  | ns:Peter | "70"^^xsd:int |
| ns:Sylvie | ns:Peter | "70"^^xsd:int |

-   List (name, age) of all French citizens. For each one, if he/she is married, display the name of his wife/her husband

```
SELECT ?name ?age ?partner
WHERE {
	 ?name ns:nationality "French" .
	 ?name ns:age ?age .
	 OPTIONAL {?name ns:isMarriedWith ?partner }
}

```

| name      | age           | partner  |
| --------- | ------------- | -------- |
| ns:Claude | "5"^^xsd:int  |          |
| ns:Peter  | "70"^^xsd:int | ns:Marie |
| ns:Marie  | "69"^^xsd:int | ns:Peter |
| ns:Thomas | "40"^^xsd:int | ns:Alex  |

-   List of the name of persons who are brother of someone

```
SELECT ?b ?pers
WHERE {
	 ?b ns:isBrotherOf ?pers .
}
```

| b       | pers       |
| ------- | ---------- |
| ns:Paul | ns:Thomas  |
| ns:Tom  | ns:Michael |

-   List of the name of persons who are daughter of someone

```
SELECT ?b ?pers
WHERE {
	 ?b ns:isDaughterOf ?pers .
}
```

| b         | pers      |
| --------- | --------- |
| ns:Chloé  | ns:Marie  |
| ns:Chloé  | ns:Peter  |
| ns:Claude | ns:Sylvie |
| ns:Sylvie | ns:Marie  |
| ns:Sylvie | ns:Peter  |

-   List of the name of persons who are uncle of someone

```
Rule :
[Uncle: (?uncle rdf:type ns:Person) (?child ns:isChildOf ?parent) (?parent ns:isBrotherOf ?uncle) -> (?uncle ns:isUncleOf ?child)]

SELECT ?b ?pers
WHERE {
	 ?b ns:isUncleOf ?pers .
}
```

-   List of the name of persons who are married

```
SELECT ?name ?partner
WHERE {
	 ?name ns:isMarriedWith ?partner
}

```

| name      | partner   |
| --------- | --------- |
| ns:Marie  | ns:Peter  |
| ns:John   | ns:Sylvie |
| ns:Sylvie | ns:John   |
| ns:Peter  | ns:Marie  |
| ns:Alex   | ns:Thomas |
| ns:Thomas | ns:Alex   |

## Part 2: Programming using the Jena API

-   Download and install [Jena](http://www-inf.it-sudparis.eu/~gaaloulw/KM/Labs/Lab3/jena-2.6.2.zip)
-   Launch Eclipse
-   Create a new Java project
-   Create a new folder called _data_
-   Create the file owlrules.txt in the folder _data_ (content is given below)
-   Create the file rules.txt in the folder _data_ (content is given below)
-   Create the file query.txt in the folder _data_ (content is given below)
-   Create the file family.owl in the folder _data_
-   Create a new package _tools_
-   Open the menu _Project/Properties/Java Build Path_. In the tab _Libraries_, click on _add external jars_. Add all the lib of Jena (see the folder in which Jena is installed).
-   Create a new class [FileTool](http://www-inf.it-sudparis.eu/~gaaloulw/KM/Labs/Lab3/FileTool.java) in _tools_ package
-   Create a new class [JenaEngine](http://www-inf.it-sudparis.eu/~gaaloulw/KM/Labs/Lab3/JenaEngine.java) in _tools_ package
-   Create a new package _applications_
-   Create a new class Main in _application_ package
-   Execute the Main class

Query n°0

| Son        |
| ---------- |
| ns:Paul    |
| ns:Pedro   |
| ns:Tom     |
| ns:Thomas  |
| ns:Michael |

Query n°1

| Daughter  |
| --------- |
| ns:Nora   |
| ns:Chlo?? |
| ns:Claude |
| ns:Sylvie |

Query n°2

| Parent    |
| --------- |
| ns:Peter  |
| ns:Thomas |
| ns:Marie  |

Query n°3

| Father    |
| --------- |
| ns:Peter  |
| ns:Thomas |

Query n°4

| Mother   |
| -------- |
| ns:Marie |

Query n°5

| GrandMother |
| ----------- |
| ns:Marie    |

Query n°6

| GrandFather |
| ----------- |
| ns:Peter    |

Query n°7

| Brother | Brother2   |
| ------- | ---------- |
| ns:Paul | ns:Thomas  |
| ns:Tom  | ns:Michael |

Query n°8

| Sister     | Sister2   |
| ---------- | --------- |
| ns:Nora    | ns:Sylvie |
| ns:Claude  | ns:Sylvie |
| ns:Thomas  | ns:Sylvie |
| ns:Marie   | ns:Sylvie |
| ns:Peter   | ns:Sylvie |
| ns:Tom     | ns:Sylvie |
| ns:Rob     | ns:Sylvie |
| ns:John    | ns:Sylvie |
| ns:Pedro   | ns:Sylvie |
| ns:Paul    | ns:Sylvie |
| ns:Michael | ns:Sylvie |
| ns:Alex    | ns:Sylvie |
| ns:Chlo??  | ns:Sylvie |

Query n°9

| name      | age           |
| --------- | ------------- |
| ns:Paul   | "38"^^xsd:int |
| ns:Thomas | "40"^^xsd:int |
| ns:Nora   | "50"^^xsd:int |
| ns:Chlo?? | "18"^^xsd:int |
| ns:Sylvie | "30"^^xsd:int |

Query n°10

| name      | father   | age           |
| --------- | -------- | ------------- |
| ns:Paul   | ns:Peter | "70"^^xsd:int |
| ns:Thomas | ns:Peter | "70"^^xsd:int |
| ns:Nora   | ns:Peter | "70"^^xsd:int |
| ns:Chlo?? | ns:Peter | "70"^^xsd:int |
| ns:Sylvie | ns:Peter | "70"^^xsd:int |

Query n°11

| name      | age           | partner  |
| --------- | ------------- | -------- |
| ns:Claude | "5"^^xsd:int  |          |
| ns:Peter  | "70"^^xsd:int | ns:Marie |
| ns:Marie  | "69"^^xsd:int | ns:Peter |
| ns:Thomas | "40"^^xsd:int | ns:Alex  |

Query n°12

| Brother | Brother2   |
| ------- | ---------- |
| ns:Paul | ns:Thomas  |
| ns:Tom  | ns:Michael |

Query n°13

| Sister     | Sister2   |
| ---------- | --------- |
| ns:Nora    | ns:Sylvie |
| ns:Claude  | ns:Sylvie |
| ns:Thomas  | ns:Sylvie |
| ns:Marie   | ns:Sylvie |
| ns:Peter   | ns:Sylvie |
| ns:Tom     | ns:Sylvie |
| ns:Rob     | ns:Sylvie |
| ns:John    | ns:Sylvie |
| ns:Pedro   | ns:Sylvie |
| ns:Paul    | ns:Sylvie |
| ns:Michael | ns:Sylvie |
| ns:Alex    | ns:Sylvie |
| ns:Chlo??  | ns:Sylvie |

Query n°14

| Uncle | Uncle2 |
| ----- | ------ |

Query n°15

| name      | partner   |
| --------- | --------- |
| ns:Rob    | ns:Nora   |
| ns:Marie  | ns:Peter  |
| ns:John   | ns:Sylvie |
| ns:Sylvie | ns:John   |
| ns:Peter  | ns:Marie  |
| ns:Alex   | ns:Thomas |
| ns:Thomas | ns:Alex   |
| ns:Nora   | ns:Rob    |

-   Create query files that correspond to query from Part1 of this lab. Modify the Main class to refer to these query (one by one) and execute the Main program (compare the results with the ones obtained in Part 1)
-   Add a new functionality in the JenaEngine class that allows to read the value of property of type ObjectType (see the javadoc of the Jena API)
-   Add a new functionality in the JenaEngine class that allows to read the value of property of type DataType (see the javadoc of the Jena API)
-   Create a new class in the application package that

    a. Read a name of a person2021 - ESILV A4 | Practice Works for
    b. Display his/her parents, brothers and sisters.
    c. If this person is married, display the name and age of her husband/his wife
    d. If this person is not married Display all the persons

    -   whose gender is different,
    -   whose age is close (+/- 5 years) and
    -   is not married
