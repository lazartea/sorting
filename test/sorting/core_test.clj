(ns sorting.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [sorting.core :as s]
            [sorting.test-utils.fixtures :as f]))

(deftest -main
  (testing "-main function reads files, parses the data, sorts the data, and returns the correct output"
    (is (= f/expected-output (with-out-str (s/-main "input-files/comma.txt" "input-files/pipe.txt" "input-files/space.txt"))))))
