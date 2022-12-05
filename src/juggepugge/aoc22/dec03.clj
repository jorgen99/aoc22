(ns juggepugge.aoc22.dec03
  (:require
    [clojure.string :as str]))


(defn line-in-half [line]
  (let [middle-idx (int (/ (count line) 2))
        char-seq-map (partition middle-idx line)]
    (map #(apply str %)  char-seq-map))) 


#_(defn part1 [lines]
   (reduce + (map parse-and-score lines)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


#_(defn part2 [lines]
   ( (preduce + (map parse-and-score2 lines))))

