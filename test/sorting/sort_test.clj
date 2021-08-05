(ns sorting.sort-test
  (:require [clojure.test :refer [deftest testing are is]]
            [sorting.test-utils.fixtures :as f]
            [sorting.test-utils.records :as r]
            [sorting.sort :as sort]))

(deftest string-to-date
  (testing "string-to-date returns what we expect"
    (are [x y] (= x y)
      #inst "1989-12-13T07:00:00.000-00:00" (sort/string-to-date "12/13/1989")
      #inst "2021-02-03T07:00:00.000-00:00" (sort/string-to-date "2/3/2021"))))

(deftest merge-compare
  (testing "merge-compare returns what we expect"
    (testing "when sorting by dob"
      (testing "ascending"
         (are [x y] (= x y)
           (- 1) (sort/merge-compare r/Abercrombie r/Bishop [:dob ]1)
           1     (sort/merge-compare r/Bishop r/Abercrombie [:dob] 1)
           0     (sort/merge-compare r/Abercrombie r/Abercrombie [:dob] 1)))

      (testing "descending"
        (are [x y] (= x y)
          1        (sort/merge-compare r/Abercrombie r/Bishop [:dob] (- 1))
          (- 1)    (sort/merge-compare r/Bishop r/Abercrombie [:dob] (- 1))
          0        (sort/merge-compare r/Abercrombie r/Abercrombie [:dob] (- 1)))))
    
    (testing "when sorting by last name"
      (testing "ascending"
        (is (neg? (sort/merge-compare r/Abercrombie r/Bishop[:last] 1)))
        (is (pos? (sort/merge-compare r/Bishop r/Abercrombie [:last] 1)))
        (is (= 0  (sort/merge-compare r/Abercrombie r/Abercrombie [:last] 1)))))
    
      (testing "descending"
        (is (pos? (sort/merge-compare r/Abercrombie r/Bishop [:last] (- 1))))
        (is (neg? (sort/merge-compare r/Bishop r/Abercrombie [:last] (- 1))))
        (is (= 0  (sort/merge-compare r/Abercrombie r/Abercrombie [:last] (- 1)))))))

(deftest sort1
  (testing "sort1 returns what we expect"
    (is (= f/records-sort1 (sort/sort-1 f/unsorted-records)))))

(deftest sort2
  (testing "sort2 returns what we expect"
    (is (= f/records-sort2 (sort/sort-2 f/unsorted-records)))))

(deftest sort3
  (testing "sort3 returns what we expect"
    (is (= f/records-sort3 (sort/sort-3 f/unsorted-records)))))

(deftest all-sort
  (testing "all-sort returns what we expect"
    (is (= f/all-sorted-records (sort/all-sort f/unsorted-records)))))