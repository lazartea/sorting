(ns sorting.sort-test
  (:require [clojure.test :refer [deftest testing are is]]
            [sorting.test-utils.fixtures :as f]
            [sorting.test-utils.records :as r]
            [sorting.sort :as sort]))

(deftest string-to-date
  (testing "string-to-date returns what we expect"
    (are [x y] (= x y)
      ;;TODO update test to be robust to timezone differences
      #inst "1989-12-13T08:00:00.000-00:00" (sort/string-to-date "12/13/1989")
      #inst "2021-02-03T08:00:00.000-00:00" (sort/string-to-date "2/3/2021"))))

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
        (is (neg? (sort/merge-compare r/Abercrombie r/Bishop [:last] 1)))
        (is (pos? (sort/merge-compare r/Bishop r/Abercrombie [:last] 1)))
        (is (= 0  (sort/merge-compare r/Abercrombie r/Abercrombie [:last] 1)))))
    
      (testing "descending"
        (is (pos? (sort/merge-compare r/Abercrombie r/Bishop [:last] (- 1))))
        (is (neg? (sort/merge-compare r/Bishop r/Abercrombie [:last] (- 1))))
        (is (= 0  (sort/merge-compare r/Abercrombie r/Abercrombie [:last] (- 1)))))))

(deftest sort-by-params
  (testing "sort-by-params returns what we expect")
    (is (= f/records-sort1 (sort/sort-by-params f/unsorted-records [:gender :last] 1)))
    (is (= f/records-sort2 (sort/sort-by-params f/unsorted-records [:dob :last] 1)))
    (is (= f/records-sort3 (sort/sort-by-params f/unsorted-records [:last] (- 1)))))

(deftest all-sort
  (testing "all-sort returns what we expect"
    (is (= f/all-sorted-records (sort/all-sort f/unsorted-records)))))