(ns sorting.sort
  "Methods which sort data.")

(defn string-to-date
  "Takes a date as a string. Returns a date object."
  [date-string]
  (.parse (java.text.SimpleDateFormat. "MM/dd/yyyy") date-string))

(defn get-diff
  "Takes two values and the key on which to compare them.
   If the key is date of birth, converts values to date objects.
   Returns an int."
  [a b key]
  (if (= key :dob)
        (compare (string-to-date a) (string-to-date b))
        (compare a b)))

(defn merge-compare
  "Takes two maps, a vector of keys on which to compare them, 
   and an integer representing the direction to compare them.
   Returns an int representing if the first record should be
   sorted above or below the second."
  [record-a record-b comp-keys direction]
  (let [comp (first comp-keys)
        second-comp (last comp-keys)
        a (comp record-a)
        b (comp record-b)
        diff (get-diff a b comp)]
    (* direction
       (if (= 0 diff)
          (get-diff (second-comp record-a) (second-comp record-b) second-comp)
           diff))))

(defn empty-check
  "Takes two maps. 
   If one map is empty, returns the other.
   If neither are empty, returns false."
  [record-a record-b]
  (cond (empty? record-a) record-b
        (empty? record-b) record-a
        :else false))

(defn merge-vals
  "Takes two vectors containing maps, a vector
   containing comparison keys, and an int 
   representing the direction to compare them."
  [record-a record-b comp-keys direction]
  (let [single-record (empty-check record-a record-b)]
    (if single-record
      single-record
      (let [diff (merge-compare (first record-a) (first record-b) comp-keys direction)]
        (cond
         (neg? diff) (cons (first record-a) (merge-vals (drop 1 record-a) record-b comp-keys direction)) 
         (pos? diff) (cons (first record-b) (merge-vals record-a (drop 1 record-b) comp-keys direction)))))))

(defn merge-sort
  "Takes a vector containing maps, a vector containing comparison keys,
   and an int representing the direction to compare them. Returns
   a vector of maps sorted by the correct keys in the correct direction."
  [records comparison direction]
  (if (< (count records) 2)
    records
    (let [mid-point (quot (count records) 2)
          [first-half second-half] (split-at mid-point records)
          result (merge-vals (merge-sort first-half comparison direction) (merge-sort second-half comparison direction) comparison direction)]
      result)))

(defn get-comparator
  "Given a vector of comparison keys and a direction, returns a 
  comparison function."
  [comp-keys direction]
  (fn [record1 record2] (merge-compare record1 record2 comp-keys direction)))

(defn sort-by-params
  "Sorts a vector of maps using comparison keys and a direction"
  [records comp-keys direction]
  (into [] (sort (get-comparator comp-keys direction) records)))

(defn all-sort
  "Takes a vector of maps. Returns a vector of vectors containing 
   sorted maps."
  [records]
  [(sort-by-params records [:gender :last] 1) (sort-by-params records [:dob :last] 1) (sort-by-params records [:last] (- 1))])