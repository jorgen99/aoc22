(ns juggepugge.aoc22-dec01
  (:require
   [clojure.string :as str]))


(defn- find-max [xs acc maxx max-fn]
  (let [max-fn (fn [xs acc maxx]
                 (if (empty? xs)
                   (max-fn acc maxx)
                   (let [x (first xs)]
                     (if (= 0 x)
                       (recur (rest xs) 0 (max-fn acc maxx))
                       (recur (rest xs) (+ x acc) maxx)))))]
    (max-fn xs acc maxx)))


(defn part1 [data]
  (find-max data 0 0 max)) 


(defn max3 [m xs]
  (sort 
    (if (> 3 (count xs))
     (conj xs m)
     (if (< (first xs) m)
       (conj (rest xs) m)
       xs))))


(defn part2 [data]
  (apply + (find-max data 0 [] max3)))

