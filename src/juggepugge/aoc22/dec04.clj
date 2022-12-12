(ns juggepugge.aoc22.dec04
  (:require
    [clojure.set :as set]
    [clojure.string :as str]))


(defn line->range-sets [line]
  (->> (str/split line #",")
       (map #(str/split % #"-"))
       flatten
       (map read-string)
       (partition 2)
       (map (fn [[from to]]
              (range from (inc to))))
       (map set)))


(defn sub-or-super? [line]
  (let [range-sets (line->range-sets line)]
    (or
      (apply set/subset? range-sets)
      (apply set/superset? range-sets))))


(defn fully-contained [lines]
  (map sub-or-super? lines))


(defn overlap? [line]
  (let [range-sets (line->range-sets line)]
    (not (empty? (apply set/intersection range-sets)))))


(defn overlaps [lines]
  (map overlap? lines))


(defn part1 [lines]
  (count
    (filter true? (fully-contained lines))))


(defn part2 [lines]
  (count
    (filter true? (overlaps lines))))

