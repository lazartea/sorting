(ns sorting.test-utils.fixtures
  (:require [sorting.test-utils.records :as record]
            [clojure.java.io :as io]))

(def pipe-initial
  ["Smith | Steve | D | M | Red | 3-3-1985" 
   "Bonk | Radek | S | M | Green | 6-3-1975" 
   "Bouillon | Francis | G | M | Blue | 6-3-1975"])

(def pipe-split
  [["Smith" "Steve" "D" "M" "Red" "3-3-1985"]
   ["Bonk" "Radek" "S" "M" "Green" "6-3-1975"]
   ["Bouillon" "Francis" "G" "M" "Blue" "6-3-1975"]])

(def pipe-formatted
  [["Smith" "Steve" "M" "3-3-1985" "Red"]
   ["Bonk" "Radek" "M" "6-3-1975" "Green"]
   ["Bouillon" "Francis" "M" "6-3-1975" "Blue"]])

(def pipe-records
  [record/Smith record/Bonk record/Bouillon])

(def comma-initial
  ["Abercrombie, Neil, Male, Tan, 2/13/1943"
   "Bishop, Timothy, Male, Yellow, 4/23/1967"
   "Kelly, Sue, Female, Pink, 7/12/1959"])

(def comma-split
  [["Abercrombie" "Neil" "Male" "Tan" "2/13/1943"]
   ["Bishop" "Timothy" "Male" "Yellow" "4/23/1967"]
   ["Kelly" "Sue" "Female" "Pink" "7/12/1959"]])

(def comma-formatted
  [["Abercrombie" "Neil" "Male" "2/13/1943" "Tan"]
   ["Bishop" "Timothy" "Male" "4/23/1967" "Yellow"]
   ["Kelly" "Sue" "Female" "7/12/1959" "Pink"]])

(def comma-records
  [record/Abercrombie record/Bishop record/Kelly])

(def space-initial
  ["Kournikova Anna F F 6-3-1975 Red"
   "Hingis Martina M F 4-2-1979 Green"
   "Seles Monica H F 12-2-1973 Black"])

(def space-split
  [["Kournikova" "Anna" "F" "F" "6-3-1975" "Red"]
   ["Hingis" "Martina" "M" "F" "4-2-1979" "Green"]
   ["Seles" "Monica" "H" "F" "12-2-1973" "Black"]])

(def space-formatted
  [["Kournikova" "Anna" "F" "6-3-1975" "Red"]
   ["Hingis" "Martina" "F" "4-2-1979" "Green"]
   ["Seles" "Monica" "F" "12-2-1973" "Black"]])

(def space-records
  [record/Kournikova record/Hingis record/Seles])

(def unsorted-records 
  [record/Smith record/Bonk record/Bouillon 
   record/Abercrombie record/Bishop record/Kelly 
   record/Kournikova record/Hingis record/Seles])

(def records-sort1
  [record/Hingis record/Kelly record/Kournikova 
   record/Seles record/Abercrombie record/Bishop
   record/Bonk record/Bouillon record/Smith])

(def records-sort1-output
  "Hingis Martina Female 4/2/1979 Green
Kelly Sue Female 7/12/1959 Pink
Kournikova Anna Female 6/3/1975 Red
Seles Monica Female 12/2/1973 Black
Abercrombie Neil Male 2/13/1943 Tan
Bishop Timothy Male 4/23/1967 Yellow
Bonk Radek Male 6/3/1975 Green
Bouillon Francis Male 6/3/1975 Blue
Smith Steve Male 3/3/1985 Red\n")

(def records-sort2
  [record/Abercrombie record/Kelly record/Bishop
   record/Seles record/Bonk record/Bouillon
   record/Kournikova record/Hingis record/Smith])

(def records-sort2-output
  "Abercrombie Neil Male 2/13/1943 Tan
Kelly Sue Female 7/12/1959 Pink
Bishop Timothy Male 4/23/1967 Yellow
Seles Monica Female 12/2/1973 Black
Bonk Radek Male 6/3/1975 Green
Bouillon Francis Male 6/3/1975 Blue
Kournikova Anna Female 6/3/1975 Red
Hingis Martina Female 4/2/1979 Green
Smith Steve Male 3/3/1985 Red\n")

(def records-sort3
  [record/Smith record/Seles record/Kournikova
   record/Kelly record/Hingis record/Bouillon
   record/Bonk record/Bishop record/Abercrombie])

(def records-sort3-output
  "Smith Steve Male 3/3/1985 Red
Seles Monica Female 12/2/1973 Black
Kournikova Anna Female 6/3/1975 Red
Kelly Sue Female 7/12/1959 Pink
Hingis Martina Female 4/2/1979 Green
Bouillon Francis Male 6/3/1975 Blue
Bonk Radek Male 6/3/1975 Green
Bishop Timothy Male 4/23/1967 Yellow
Abercrombie Neil Male 2/13/1943 Tan\n")

(def all-sorted-records
  [records-sort1 records-sort2 records-sort3])

(def expected-output
  (slurp (io/resource "expected_output.txt")))