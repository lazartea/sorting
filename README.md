# Sorting

A simple project which reads in data from local files, sorts the data, and prints the results to the console.

## Acceptance Criteria
Create a command line application. The program should:
  * Read the files from your local environment.
  * Print out all the input data sorted together in the following ways:
    * Sort 1 - by gender (females before males) then by last name ascending
    * Sort 2 - by birthdate, ascending then by last name ascending
    * Sort 3 - by last name, descending

For all three sorts, ensure that fields are displayed in the following order:
  * last name, first name, gender, date of birth, favorite color 
  * Dates should be displayed in the format M/D/YYYY.

The program output should match the contents of the `expected_output.txt` file.

## File Structure
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

## Data Files
Data files must be placed in the resources directory. The current files are used for testing purposes,
but more can be added as long as they are .txt files which follow one of the following formats:
* `LastName | FirstName | MiddleInitial | Gender | FavoriteColor | DateOfBirth`
* `LastName, FirstName, Gender, FavoriteColor, DateOfBirth`
* `LastName FirstName MiddleInitial Gender DateOfBirth FavoriteColor`

## How to run
* To run call `lein run` along with one or more file paths. The file paths should not include the resources directory.
  * Ex: `lein run "input-files/comma.txt" "input-files/pipe.txt" "input-files/space.txt"`
* To run all tests: `lein test :all`
* To run a single test file: `lein test :only <namespace>`
  * `lein test :only sorting.parse-test` runs all of the tests in the `test/sorting/parse_test.clj` namespace. 
  * `lein test :only sorting.parse-test/create-records` tests only the `create-records` test within the `test/sorting/parse_test.clj` namespace.

## How to build
* `lein uberjar`
* `java -jar target/uberjar/sorting.jar`

## Version Requirements:
* Lein 2.8.1 
  * Installation instructions [here](https://leiningen.org/)
* Clojure 1.8.0
  * Installation instructions [here](https://clojure.org/guides/getting_started#_clojure_installer_and_cli_tools)
  * Requires either Java 8 or Java 11, and that the java command is on the path or that the JAVA_HOME environment variable is set.
