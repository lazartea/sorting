(ns sorting.core
  (:gen-class)
  (:require [sorting.parse :as parse]
            [sorting.sort :as sort]
            [sorting.output :as out]))

(defn -main
  "Entrypoint. Calls sorting methods on parsed records 
   and outputs formatted results to the console."
  [&]
  (-> (sort/all-sort parse/create-records)
      (out/output)))
