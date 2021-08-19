(ns sorting.parse
  "Methods which read from files and format their data."
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

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
    (with-open [reader (io/reader (io/resource path))]
      (reduce conj [] (line-seq reader)))
    ;;if the path doesn't exist in the resources dir, 
    ;;(io/resource path) will return nil, and we will see
    ;;an IllegalArgumentException when calling 
    ;;io/reader on nil
    (catch java.lang.IllegalArgumentException e
      (println (format "Input file %s is not in the resources directory." path)))
    (catch Exception e
      (println (str "An unexpected error occurred: " e)))))

(defn get-indices
  [delimiter]
  (case (str delimiter)
    " " [0 1 3 4 5]
    ", " [0 1 2 4 3]
    "\\s\\|\\s" [0 1 3 5 4]))

(defn get-delimiter
  [string]
  (let [delimiters [#"\s\|\s", #", ", #" "]]
    (first (filter (fn [delim] (re-find delim string)) delimiters))))

(defn split-file-content
  "Takes a delimiter and a vector of strings.
   Separates each string by delimiter. Returns
   a vector of vectors containing strings."
  [content]
  (let [delimiter (get-delimiter (first content))]
    (mapv #(str/split % delimiter) content)))

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

(defn create-records
  [file-paths]
  "Reads each file line by line. 
   Splits file content by delimiter.
   Formats file content correctly.
   Returns a vector of maps based on formatted data"
  (into []
    (flatten
      (for [path file-paths]
        (let [file-contents (read-file path)
              indices (get-indices (get-delimiter (first file-contents)))]
          (->> (split-file-content file-contents)
               (format-file-content indices)
               (build-records)))))))