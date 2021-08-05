(ns sorting.output
  "Methods which output to the console."
  (:require [clojure.string :as string]))

(defn print-single-record
  "Takes a vector containing record maps.
   Prints each record to the console one line at a time."
  [rows]
  (dorun (map (fn [record]
                (println (string/join " " (vals record)))) rows)))

(defn output
  "Takes a vector containing sorted vectors of record maps.
   Outputs each sorted list to the console one line at a time, 
   separated by a heading."
  [sorted-lists]
  (loop [index 0]
      (when (< index 3)
         (println (format "Output %s:" (inc index)))
         (print-single-record (get sorted-lists index))
        
        ;;this prevents an extra newline at the end
         (when (< index 2)
           (newline))
      (recur (inc index)))))