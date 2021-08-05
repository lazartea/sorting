(ns sorting.output-test
  (:require [clojure.test :refer [deftest testing are is]]
            [sorting.test-utils.fixtures :as f]
            [sorting.output :as o]))

(deftest single-output
  (testing "single-output returns what we expect"
    (are [x y] (= x y)
      f/records-sort1-output (with-out-str (o/print-single-record f/records-sort1))
      f/records-sort2-output (with-out-str (o/print-single-record f/records-sort2))
      f/records-sort3-output (with-out-str (o/print-single-record f/records-sort3)))))

(deftest output
  (testing "output returns what we expect"
    (is (= f/expected-output (with-out-str (o/output f/all-sorted-records))))))
