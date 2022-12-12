(ns juggepugge.aoc22.dec03
  (:require
    [clojure.string :as str]
    [clojure.set :as clojure-set]))


(defn line-in-half [line]
  (let [middle-idx (int (/ (count line) 2))
        char-seq-map (partition middle-idx line)]
    (map #(apply str %)  char-seq-map)))

(defn common-letter [[left right]]
   (first
     (clojure-set/intersection
      (set (seq left))
      (set (seq right))))) 

(defn prio [ch]
  (if (< (int ch) (int \a))
    (+ 27 (- (int ch) (int \A)))
    (- (int ch) 96)))

(defn prio-for-line [line]
  (-> line
      seq
      line-in-half
      common-letter
      prio))


(defn part1 [lines]
  (reduce
    (fn [acc line]
      (+ acc (prio-for-line line)))
    0
    lines))
;;


#_(defn part2 [lines]
   ( (preduce + (map parse-and-score2 lines))))

