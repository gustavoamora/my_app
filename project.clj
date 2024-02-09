(defproject tsp "0.1.0-SNAPSHOT"
  :description "Solução do problema do Caixeiro Viajante em Clojure"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/math.combinatorics "0.2.0"]]
  :main tsp.core/-main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
