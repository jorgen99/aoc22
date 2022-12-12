(ns repl
  (:require
    [clojure.string :as str]
    [clojure.set :as set]))



(let [#_#_lines (slurp-test-data "resources/04_test.txt")
      l1 "2-4,6-8" 
      l2 "2-3,4-5"
      l3 "2-8,3-7"
      l4 "6-6,4-6"
      line-sets (fn [line]
                  (->> (str/split line #",")
                      (map #(str/split % #"-")) 
                      flatten
                      (map read-string)
                      (partition 2)
                      (map (fn [[from to]]
                             (range from (inc to))))
                      (map set)))]
  (map #(or 
          (apply set/subset? (line-sets %))
          (apply set/superset? (line-sets %)))
       [l1 l2 l3 l4]))

