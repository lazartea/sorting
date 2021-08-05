(ns sorting.parse
  "Methods which read from files and format their data."
  (:import java.io.FileNotFoundException)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def pipe-file
  {:type "pipe"
   :path (io/resource "input-files/pipe.txt")
   :delimiter #"\s\|\s"
   :indices [0 1 3 5 4]})

(def space-file
  {:type "space"
   :path (io/resource "input-files/space.txt")
   :delimiter #" "
   :indices [0 1 3 4 5]})

(def comma-file
  {:type "space"
   :path (io/resource "input-files/comma.txt")
   :delimiter #", "
   :indices [0 1 2 4 3]})

(defn standardize-gender
  [gender]
  (case gender
    "M" "Male"
    "F" "Female"
    gender))

(defn standardize-dob
  [dob]
  (clojure.string/replace dob #"-" "/"))

(defn record
  [values]
  (let [[last first gender dob color] values]
    {:last last
     :first first
     :gender (standardize-gender gender)
     :dob (standardize-dob dob)
     :color color}))

(defn read-file
  "Takes a path. Reads file and returns
   a vector of strings for each line in the file."
  [path]
  (try
    (with-open [reader (io/reader path)]
      (reduce conj [] (line-seq reader)))
    (catch FileNotFoundException e
      (println (format "Input file %s is not in the resources directory." path)))
    (catch Exception e
      (println (str "An unexpected error occurred: " e)))))

(defn split-file-content
  "Takes a delimiter and a vector of strings.
   Separates each string by delimiter. Returns
   a vector of vectors containing strings."
  [delimiter content]
  (mapv #(str/split % delimiter) content))

(defn format-file-content
  "Takes a vector of vectors of strings as well
   as the indices of interest. Returns a vector of
   vectors of strings containing only the strings
   of interest."
  [indices content]
  (mapv
   (fn [line]
     (mapv (fn [index] (get line index)) indices))
   content))

(defn build-records
  "Takes a vector of strings.
   Returns a vector of maps."
  [content]
  (mapv #(record %) content))

(def create-records
  "Reads each file line by line. 
   Splits file content by delimiter.
   Formats file content correctly.
   Returns a vector of maps based on formatted data"
  (flatten 
   (into [] 
         (for [file [pipe-file comma-file space-file]]
           (let [{path :path
                  indices :indices
                  delimiter :delimiter} file]

             (->> (read-file path)
                  (split-file-content delimiter)
                  (format-file-content indices)
                  (build-records)))))))