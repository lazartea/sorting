# Sorting

A simple project which reads in data from local files, sorts the data, and prints the results to the console.

## File Structure
### resources dir
The resources directory contains input files as well as an `expected_output.txt` file used for testing. 

The Input files are located in the `resources\input-files` directory. Each line in each files contains data in one of the following formats:

* `LastName | FirstName | MiddleInitial | Gender | FavoriteColor | DateOfBirth`
* `LastName, FirstName, Gender, FavoriteColor, DateOfBirth`
* `LastName FirstName MiddleInitial Gender DateOfBirth FavoriteColor`

### src/sorting directory
The `src/sorting` directory contains four files:
* `src/sorting/core.clj`: This contains the `-main` function.
* `src/sorting/parse.clj`: This file reads in the data from each of the three input files. The data is converted into an unsorted vector consisting of individual records which represent each line of data. The records are maps in the following structure:
```
 {:last <Last name as a string>
  :first <first name as a string>
  :gender <"Female" or "Male">
  :dob <date of birth as a string in the format MM/DD/YYYY>
  :color <favorite color as a string>}
```
* `src/sorting/sort.clj`: This file takes the unsorted vector of records and sorts them using a merge sort algorithm in three different orders: 
  * Sort 1 - by gender (females before males) then by last name ascending 
  * Sort 2 - by birth date, ascending then by last name ascending
  * Sort 3 - by last name, descending

* `src/sorting/output.clj`: This file takes the sorted records and outputs them to the console. A heading is printed before each sort. Each record is printed on its own line in the following format:
  * Last name, First name, Gender, Date of birth, Favorite color

Test files for each of these namespaces are located at `test/sorting`. `test/sorting/test_utils` contains fixtures that are used for testing purposes. 

## How to run
* To run: `lein run`
* To run tests: `lein test :all`
* To run a single test file: `lein test :only <namespace>`
  * For example, `lein test :only sorting.parse-test` runs all of the tests in the `test/sorting/parse_test.clj` namespace. `lein test :only sorting.parse-test/create-records` tests only the `create-records` test within the `test/sorting/parse_test.clj` namespace.

## How to build
* `lein uberjar`
* `java -jar target/uberjar/sorting.jar`

## Version Requirements:
* Lein 2.8.1 
  * Installation instructions [here](https://leiningen.org/)
* Clojure 1.8.0
  * Installation instructions [here](https://clojure.org/guides/getting_started#_clojure_installer_and_cli_tools)
  * Requires either Java 8 or Java 11, and that the java command is on the path or that the JAVA_HOME environment variable is set.
