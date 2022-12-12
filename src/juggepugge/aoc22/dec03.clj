(ns juggepugge.aoc22.dec03
  (:require
    [clojure.set :as clojure-set]
    [clojure.string :as str]))


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


(defn line-intersections [lines]
  (reduce (fn [acc line]
            (clojure-set/intersection
              (set (seq line))
              acc))
          (set (seq (first lines)))
          lines))


(defn prio-for-lines [lines]
  (->
    lines
    line-intersections
    first
    prio))


(defn part1 [lines]
  (reduce
    (fn [acc line]
      (+ acc (prio-for-line line)))
    0
    lines))


(defn part2 [lines]
  (reduce
    (fn [acc three-lines]
      (+ acc (prio-for-lines three-lines)))
    0
    (partition 3 lines)))

