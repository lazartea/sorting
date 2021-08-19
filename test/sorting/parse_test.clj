(ns sorting.parse-test
  (:require [clojure.test :refer [deftest testing is are]]
            [sorting.test-utils.fixtures :as f]
            [sorting.parse :as p]))

(deftest standardize-gender
  (testing "standardize-gender returns what we expect"
    (are [x y] (= x y)
      "Female" (p/standardize-gender "F")
      "Male"   (p/standardize-gender "M")
      "Female" (p/standardize-gender "Female")
      "Male" (p/standardize-gender "Male"))))

(deftest standardize-dob
  (testing "standardize-dob returns what we expect"
    (are [x y] (= x y)
      "12/13/1989" (p/standardize-dob "12-13-1989")
      "12/13/1989" (p/standardize-dob "12/13/1989")
      "11/19/2021" (p/standardize-dob "11-19-2021")
      "11/19/2021" (p/standardize-dob "11/19/2021"))))

(deftest read-file
  (testing "read-file returns what we expect when file exists"
    (are [x y] (= x y)
      f/pipe-initial   (p/read-file "input-files/pipe.txt")
      f/space-initial  (p/read-file "input-files/space.txt")
      f/comma-initial  (p/read-file "input-files/comma.txt")))
             
  (testing "read-file handles error when file does not exist"
    (let [response (with-out-str (p/read-file "fake-file.txt"))]
      (is (= response "Input file fake-file.txt is not in the resources directory.\n")))))

(deftest split-file-content
  (testing "split-file-content returns what we expect"
    (are [x y] (= x y)
      f/pipe-split  (p/split-file-content f/pipe-initial)
      f/space-split (p/split-file-content f/space-initial)
      f/comma-split (p/split-file-content f/comma-initial))))

(deftest format-file-content
  (testing "format-file-content returns what we expect"
    (are [x y] (= x y)
      f/pipe-formatted  (p/format-file-content [0 1 3 5 4] f/pipe-split)
      f/space-formatted (p/format-file-content [0 1 3 4 5] f/space-split)
      f/comma-formatted (p/format-file-content [0 1 2 4 3] f/comma-split))))

(deftest build-records
  (testing "build-records returns what we expect"
    (are [x y] (= x y)
      f/pipe-records  (p/build-records f/pipe-formatted)
      f/space-records (p/build-records f/space-formatted)
      f/comma-records (p/build-records f/comma-formatted))))

(deftest create-records
  (testing "build-records returns what we expect"
    (is (= f/unsorted-records (p/create-records ["input-files/pipe.txt" "input-files/comma.txt" "input-files/space.txt"])))))
