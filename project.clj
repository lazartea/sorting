(defproject sorting "0.1.0-SNAPSHOT"
  :description "sorting code test"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot sorting.core
  :target-path "target/%s"
  :uberjar-name "sorting.jar"
  :profiles {:uberjar {:aot :all}})
